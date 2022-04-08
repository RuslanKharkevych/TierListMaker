package me.khruslan.tierlistmaker.repository.db

import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.utils.extensions.readOrDefault
import kotlinx.coroutines.CoroutineDispatcher

private const val KEY_TIER_LISTS = "tier-lists"

/**
 * [PaperRepository] implementation. Implemented with [Paper] database.
 * All functions are running in [Dispatchers.IO] context.
 *
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class PaperRepositoryImpl(private val dispatcherProvider: DispatcherProvider): PaperRepository {

    override suspend fun getTierLists(): MutableList<TierList> {
        return withContext(dispatcherProvider.io) {
            Paper.book().readOrDefault(KEY_TIER_LISTS, mutableListOf())
        }
    }


    override suspend fun saveTierList(tierList: TierList) {
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