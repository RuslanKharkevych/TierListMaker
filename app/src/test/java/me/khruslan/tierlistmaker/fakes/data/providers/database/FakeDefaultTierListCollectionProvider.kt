package me.khruslan.tierlistmaker.fakes.data.providers.database

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProvider

class FakeDefaultTierListCollectionProvider : DefaultTierListCollectionProvider {

    var tierLists = mutableListOf<TierList>()

    override var collectionProvided = false

    override fun provideCollection(): MutableList<TierList> {
        collectionProvided = true
        return tierLists
    }
}