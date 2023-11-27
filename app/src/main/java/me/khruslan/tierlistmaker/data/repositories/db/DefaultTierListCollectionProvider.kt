package me.khruslan.tierlistmaker.data.repositories.db

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of the default tier list collection that is written to the database when user opens
 * the application for the first time.
 */
interface DefaultTierListCollectionProvider {

    /**
     * Whether the collection has already been provided.
     */
    val collectionProvided: Boolean


    /**
     * Provides a default collection and marks it as provided.
     *
     * @return list of created tier lists.
     */
    fun provideCollection(): MutableList<TierList>
}