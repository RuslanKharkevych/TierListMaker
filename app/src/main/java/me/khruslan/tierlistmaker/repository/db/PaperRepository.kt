package me.khruslan.tierlistmaker.repository.db

import io.paperdb.Paper
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.utils.extensions.readOrDefault

private const val KEY_TIER_LISTS = "tier-lists"

class PaperRepository(private val dispatcherProvider: DispatcherProvidable) {
    suspend fun getTierLists(): MutableList<TierList> = withContext(dispatcherProvider.io) {
        Paper.book().readOrDefault(KEY_TIER_LISTS, mutableListOf())
    }

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