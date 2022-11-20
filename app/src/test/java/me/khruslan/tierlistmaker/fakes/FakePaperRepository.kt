package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository

class FakePaperRepository : PaperRepository {
    var fakeTierLists: MutableList<TierList>? = null
    var shouldSaveSuccessfully = true

    var savedTierList: TierList? = null
        private set

    override suspend fun getTierLists() = fakeTierLists

    override suspend fun saveTierList(tierList: TierList): Boolean {
        savedTierList = tierList
        return shouldSaveSuccessfully
    }
}