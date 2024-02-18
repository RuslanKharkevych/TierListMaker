package me.khruslan.tierlistmaker.data.providers.database

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of the default [TierList] collection that is written to the database when user opens
 * the application for the first time.
 *
 * All functions are synchronous. All exceptions must be handled internally.
 */
interface DefaultTierListCollectionProvider {

    /**
     * Whether the collection has already been provided.
     *
     * This flag must be persisted in the local storage.
     */
    val collectionProvided: Boolean

    /**
     * Provides a default collection and marks it as provided.
     *
     * If collection can't be provided due to an error, returns empty list. Regardless of the
     * operation result, [collectionProvided] flag must be updated to true.
     *
     * @return List of created tier lists.
     */
    fun provideCollection(): MutableList<TierList>
}