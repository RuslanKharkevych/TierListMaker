package me.khruslan.tierlistmaker.data.providers.tierlist

import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent

/**
 * Processor that converts [DragEffect] into [TierListEvent].
 *
 * Implementations might also update the tier list as a result of the effect.
 */
interface TierListProcessor {

    /**
     * Attaches tier list to the processor.
     *
     * It is required to set tier list before calling [processDragEffect].
     *
     * @param tierList Tier list to set.
     */
    fun setTierList(tierList: TierList)

    /**
     * Converts drag effect into tier list event.
     *
     * It is required to [setTierList] before calling this function.
     *
     * @param effect Drag effect to process.
     * @return Resulting tier list event.
     */
    fun processDragEffect(effect: DragEffect): TierListEvent
}