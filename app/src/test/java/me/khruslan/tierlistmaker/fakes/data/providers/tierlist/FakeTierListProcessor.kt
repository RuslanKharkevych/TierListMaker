package me.khruslan.tierlistmaker.fakes.data.providers.tierlist

import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessor

class FakeTierListProcessor : TierListProcessor {

    var initializedTierList: TierList? = null
        private set

    val events = mutableMapOf<DragEffect, TierListEvent>()

    override fun setTierList(tierList: TierList) {
        initializedTierList = tierList
    }

    override fun processDragEffect(effect: DragEffect) = checkNotNull(events[effect])
}