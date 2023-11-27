package me.khruslan.tierlistmaker.fakes.data.providers.db

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.db.DefaultTierListCollectionProvider

class FakeDefaultTierListCollectionProvider : DefaultTierListCollectionProvider {

    var tierLists = mutableListOf<TierList>()

    override var collectionProvided = false

    override fun provideCollection(): MutableList<TierList> {
        collectionProvided = true
        return tierLists
    }
}