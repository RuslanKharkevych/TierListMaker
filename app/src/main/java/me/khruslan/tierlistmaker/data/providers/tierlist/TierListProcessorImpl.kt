package me.khruslan.tierlistmaker.data.providers.tierlist

import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.effects.*
import me.khruslan.tierlistmaker.data.models.tierlist.*
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.util.updateLast
import timber.log.Timber
import javax.inject.Inject

/**
 * [TierListProcessor] implementation.
 */
class TierListProcessorImpl @Inject constructor(): TierListProcessor {

    /**
     * Tier list on which the processor operates.
     */
    private lateinit var tierList: TierList

    /**
     * [ResourceImage] that represents a drag target.
     */
    private val targetImage by lazy {
        ResourceImage(resId = R.drawable.ic_crop_free)
    }

    override fun setTierList(tierList: TierList) {
        this.tierList = tierList
    }

    override fun processDragEffect(effect: DragEffect): TierListEvent {
        Timber.i("Processing drag effect: $effect")
        return when (effect) {
            is HighlightEffect -> tierList.processHighlightEffect(effect)
            is InsertEffect -> tierList.processInsertEffect(effect)
            is RemoveEffect -> tierList.processRemoveEffect(effect)
            is UpdateEffect -> tierList.processUpdateEffect(effect)
        }.also { event ->
            Timber.i("Applied tier list event: $event. Tier list: $tierList")
        }
    }

    /**
     * Converts [HighlightEffect] into [TierListEvent].
     *
     * @param effect highlight effect to process.
     * @return Resulting [TierListEvent].
     */
    private fun TierList.processHighlightEffect(effect: HighlightEffect): TierListEvent {
        return when (effect) {
            is HighlightInBacklog -> {
                backlogImages.add(effect.itemPosition, targetImage)
                BacklogItemInserted(effect.itemPosition)
            }
            is HighlightInTier -> {
                tiers[effect.tierPosition].images.add(effect.itemPosition, targetImage)
                TierChanged(effect.tierPosition)
            }
            is HighlightLastInTier -> {
                tiers[effect.tierPosition].images += targetImage
                TierChanged(effect.tierPosition)
            }
            is HighlightLastInBacklog -> {
                backlogImages += targetImage
                BacklogItemInserted(backlogImages.lastIndex)
            }
            is HighlightTrashBin -> TrashBinHighlightUpdated(true)
        }
    }

    /**
     * Converts [InsertEffect] into [TierListEvent].
     *
     * @param effect insert effect to process.
     * @return Resulting [TierListEvent].
     */
    private fun TierList.processInsertEffect(effect: InsertEffect): TierListEvent {
        return when (effect) {
            is InsertToBacklog -> {
                backlogImages.add(effect.data.itemPosition, effect.data.image)
                BacklogItemInserted(effect.data.itemPosition)
            }
            is InsertToTier -> {
                tiers[effect.data.tierPosition].images.add(effect.data.itemPosition, effect.data.image)
                TierChanged(effect.data.tierPosition)
            }
            is InsertToEndOfBacklog -> {
                backlogImages += effect.image
                BacklogItemInserted(backlogImages.lastIndex)
            }
            is InsertToEndOfTier -> {
                tiers[effect.tierPosition].images += effect.image
                TierChanged(effect.tierPosition)
            }
        }
    }

    /**
     * Converts [RemoveEffect] into [TierListEvent].
     *
     * @param effect remove effect to process.
     * @return Resulting [TierListEvent].
     */
    private fun TierList.processRemoveEffect(effect: RemoveEffect): TierListEvent {
        return when (effect) {
            is RemoveFromBacklog -> {
                backlogImages.removeAt(effect.itemPosition)
                BacklogDataChanged
            }
            is RemoveFromTier -> {
                tiers[effect.tierPosition].images.removeAt(effect.itemPosition)
                TierChanged(effect.tierPosition)
            }
            is RemoveLastFromBacklog -> {
                backlogImages.removeLast()
                BacklogDataChanged
            }
            is RemoveLastFromTier -> {
                tiers[effect.tierPosition].images.removeLast()
                TierChanged(effect.tierPosition)
            }
            is UnhighlightTrashBin -> TrashBinHighlightUpdated(false)
        }
    }

    /**
     * Converts [UpdateEffect] into [TierListEvent].
     *
     * @param effect update effect to process.
     * @return Resulting [TierListEvent].
     */
    private fun TierList.processUpdateEffect(effect: UpdateEffect): TierListEvent {
        return when (effect) {
            is UpdateInBacklog -> {
                backlogImages[effect.data.itemPosition] = effect.data.image
                BacklogDataChanged
            }
            is UpdateInTier -> {
                tiers[effect.data.tierPosition].images[effect.data.itemPosition] = effect.data.image
                TierChanged(effect.data.tierPosition)
            }
            is UpdateLastInBacklog -> {
                backlogImages.updateLast(effect.image)
                BacklogDataChanged
            }
            is UpdateLastInTier -> {
                tiers[effect.tierPosition].images.updateLast(effect.image)
                TierChanged(effect.tierPosition)
            }
            is ThrowToTrashBin -> ImageRemoved
        }
    }
}