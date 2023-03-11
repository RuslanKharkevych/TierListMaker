package me.khruslan.tierlistmaker.fakes.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProvider

class FakeUpdateTierListsArgsProvider : UpdateTierListsArgsProvider {
    override var tierLists: List<TierList>? = null
}