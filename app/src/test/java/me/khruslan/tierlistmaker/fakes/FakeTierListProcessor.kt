package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.TierListEvent
import me.khruslan.tierlistmaker.repository.tierlist.TierListProcessor

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