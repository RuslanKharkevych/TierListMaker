package me.khruslan.tierlistmaker.data.repositories.tierlist

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Creator of new tier lists.
 */
interface TierListCreator {

    /**
     * Creates a new empty tier list.
     *
     * @param title title of the tier list.
     * @return created tier list.
     */
    suspend fun newTierList(title: String): TierList
}