package me.khruslan.tierlistmaker.data.providers.database

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Local storage provider. Used for storing tier lists.
 */
interface DatabaseHelper {
    /**
     * Fetches all saved tier lists or empty list of no saved tier lists found. If it's the first
     * application launch, returns default tier list collection.
     *
     * @return list of [TierList] objects or **null** if error occurred.
     */
    suspend fun getTierLists(): MutableList<TierList>?

    /**
     * Adds the new [TierList] to the database or updates existing one,
     * if a tier list with the same [TierList.id] already exists in the database
     *
     * @param tierList tier list to save.
     * @return Whether [TierList] was saved successfully.
     */
    suspend fun saveTierList(tierList: TierList): Boolean

    /**
     * Removes [TierList] from the database by identifier.
     *
     * @param id identifier of the tier list to remove.
     * @return **true** if [TierList] was removed, **false** if [TierList] with given identifier
     * doesn't exist or transaction has failed.
     */
    suspend fun removeTierListById(id: String): Boolean

    /**
     * Replaces all tier lists with the new ones.
     *
     * @param tierLists updated tier lists.
     * @return Whether transaction was successful.
     */
    suspend fun updateTierLists(tierLists: MutableList<TierList>): Boolean
}