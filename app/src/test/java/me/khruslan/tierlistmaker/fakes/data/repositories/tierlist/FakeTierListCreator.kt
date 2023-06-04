package me.khruslan.tierlistmaker.fakes.data.repositories.tierlist

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListCreator

class FakeTierListCreator : TierListCreator {
    var tierLists = listOf<TierList>()
    override suspend fun newTierList(title: String) = tierLists.first { it.title == title }
}