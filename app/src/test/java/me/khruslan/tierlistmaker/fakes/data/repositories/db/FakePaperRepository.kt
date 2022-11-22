package me.khruslan.tierlistmaker.fakes.data.repositories.db

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository

class FakePaperRepository : PaperRepository {

    var tierLists: MutableList<TierList>? = null
    var shouldSaveSuccessfully = true

    override suspend fun getTierLists() = tierLists

    override suspend fun saveTierList(tierList: TierList): Boolean {
        if (!shouldSaveSuccessfully) return false
        tierLists?.add(tierList) ?: run { tierLists = mutableListOf(tierList) }
        return true
    }
}