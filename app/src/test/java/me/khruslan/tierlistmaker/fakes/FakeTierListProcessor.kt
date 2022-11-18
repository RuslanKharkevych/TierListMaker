package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListProcessor

class FakeTierListProcessor : TierListProcessor {
    var initializedTierList: TierList? = null
    val fakeEvents = ArrayDeque<TierListEvent>()
    val processedDragEffects = ArrayDeque<DragEffect>()

    override fun setTierList(tierList: TierList) {
        initializedTierList = tierList
    }

    override fun processDragEffect(effect: DragEffect): TierListEvent {
        processedDragEffects.addLast(effect)
        return fakeEvents.removeFirst()
    }
}