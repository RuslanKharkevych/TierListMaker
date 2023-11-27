package me.khruslan.tierlistmaker.data.providers.tierlist

import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent

/**
 * Processor that converts [DragEffect] into [TierListEvent].
 */
interface TierListProcessor {

    /**
     * Attaches [TierList] to the processor.
     * It is required to set tier list before calling [processDragEffect].
     *
     * @param tierList [TierList] to set.
     */
    fun setTierList(tierList: TierList)

    /**
     * Converts [DragEffect] into [TierListEvent].
     * It is required to [setTierList] before calling this function.
     *
     * @param effect drag effect to process.
     * @return Resulting [TierListEvent].
     */
    fun processDragEffect(effect: DragEffect): TierListEvent
}