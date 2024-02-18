package me.khruslan.tierlistmaker.data.providers.database

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Local storage provider.
 *
 * Used for persisting tier lists. All functions are asynchronous. The subclasses must ensure that
 * all functions can be safely called from the main thread. All exceptions must be handled
 * internally. The result of the transaction is returned as a nullable type for read operations and
 * a boolean flag for write operations.
 */
interface DatabaseHelper {

    /**
     * Fetches all saved tier lists.
     *
     * Can return empty list if no tier lists are found.
     *
     * @return Fetched tier lists or null if error occurred.
     */
    suspend fun getTierLists(): MutableList<TierList>?

    /**
     * Saves the tier list in the database.
     *
     * If a tier list with the same ID already exists in the database, replaces the existing one.
     * Otherwise a new tier list is added.
     *
     * @param tierList tier list to save.
     * @return Whether the tier list was saved successfully.
     */
    suspend fun saveTierList(tierList: TierList): Boolean

    /**
     * Removes tier list from the database by identifier.
     *
     * In case tier list with the provided identifier doesn't exist the transaction will fail.
     *
     * @param id Identifier of the tier list to remove.
     * @return Whether the tier list was removed successfully.
     */
    suspend fun removeTierListById(id: String): Boolean

    /**
     * Replaces all tier lists with the new ones.
     *
     * @param tierLists Tier lists to save.
     * @return Whether tier lists were updated successfully.
     */
    suspend fun updateTierLists(tierLists: MutableList<TierList>): Boolean
}