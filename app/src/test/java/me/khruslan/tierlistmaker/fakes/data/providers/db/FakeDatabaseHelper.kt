package me.khruslan.tierlistmaker.fakes.data.providers.db

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.db.DatabaseHelper

class FakeDatabaseHelper : DatabaseHelper {

    var tierLists: MutableList<TierList>? = null
    var shouldSaveSuccessfully = true
    var shouldRemoveSuccessfully = true
    var shouldUpdateSuccessfully = true

    override suspend fun getTierLists() = tierLists?.toMutableList()

    override suspend fun saveTierList(tierList: TierList): Boolean {
        if (!shouldSaveSuccessfully) return false
        tierLists?.add(tierList) ?: run { tierLists = mutableListOf(tierList) }
        return true
    }

    override suspend fun removeTierListById(id: String): Boolean {
        if (!shouldRemoveSuccessfully) return false
        val tierList = tierLists?.find { it.id == id } ?: return false
        tierLists?.remove(tierList)
        return true
    }

    override suspend fun updateTierLists(tierLists: MutableList<TierList>): Boolean {
        if (!shouldUpdateSuccessfully) return false
        this.tierLists = tierLists.toMutableList()
        return true
    }
}