package me.khruslan.tierlistmaker.fakes.data.providers.tierlist

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator

class FakeTierListCreator : TierListCreator {
    var tierLists = listOf<TierList>()
    override suspend fun newTierList(title: String) = tierLists.first { it.title == title }
}