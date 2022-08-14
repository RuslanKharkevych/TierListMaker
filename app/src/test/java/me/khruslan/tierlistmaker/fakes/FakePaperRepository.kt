package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.db.PaperRepository

class FakePaperRepository : PaperRepository {
    var fakeTierLists: MutableList<TierList>? = null
    var shouldSaveSuccessfully = true
    var savedTierList: TierList? = null

    override suspend fun getTierLists() = fakeTierLists

    override suspend fun saveTierList(tierList: TierList): Boolean {
        savedTierList = tierList
        return shouldSaveSuccessfully
    }
}