package me.khruslan.tierlistmaker.repository.db

import me.khruslan.tierlistmaker.data.tierlist.TierList

/**
 * Local storage repository. Used for storing tier lists.
 */
interface PaperRepository {
    /**
     * Fetches all saved tier lists or empty list of no saved tier lists found.
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
}