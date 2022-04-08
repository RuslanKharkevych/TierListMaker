package me.khruslan.tierlistmaker.repository.db

import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.utils.extensions.readOrDefault
import kotlinx.coroutines.CoroutineDispatcher

private const val KEY_TIER_LISTS = "tier-lists"

/**
 * Local storage repository. Used for storing tier lists. Implemented with [Paper] database.
 * All functions are running in [Dispatchers.IO] context.
 *
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class PaperRepository(private val dispatcherProvider: DispatcherProvidable) {

    /**
     * Fetches all saved tier lists or empty list of no saved tier lists found.
     *
     * @return list of [TierList] objects.
     */
    suspend fun getTierLists(): MutableList<TierList> {
        return withContext(dispatcherProvider.io) {
            Paper.book().readOrDefault(KEY_TIER_LISTS, mutableListOf())
        }
    }

    /**
     * Adds the new [TierList] to the database or updates existing one,
     * if a tier list with the same [TierList.id] already exists in the database
     *
     * @param tierList tier list to save.
     */
    suspend fun saveTierList(tierList: TierList) {
        withContext(dispatcherProvider.io) {
            val tierLists = getTierLists()
            val index = tierLists.indexOfFirst { it.id == tierList.id }

            if (index == -1) {
                tierLists += tierList
            } else {
                tierLists[index] = tierList
            }

            Paper.book().write(KEY_TIER_LISTS, tierLists)
        }
    }
}