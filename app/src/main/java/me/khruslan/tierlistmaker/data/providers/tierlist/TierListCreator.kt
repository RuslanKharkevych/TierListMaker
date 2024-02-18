package me.khruslan.tierlistmaker.data.providers.tierlist

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Creator of new tier lists.
 *
 * Tier list creation is asynchronous. The subclasses must ensure that it can be safely run on the
 * main thread.
 */
interface TierListCreator {

    /**
     * Creates a new empty tier list.
     *
     * Zoom value and tiers count are fetched from user preferences.
     *
     * @param title Title of the tier list.
     * @return Created tier list.
     */
    suspend fun newTierList(title: String): TierList
}