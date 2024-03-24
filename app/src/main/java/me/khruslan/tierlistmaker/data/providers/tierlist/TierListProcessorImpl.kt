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
 *
 * @constructor Creates a new tier list processor instance.
 */
class TierListProcessorImpl @Inject constructor(): TierListProcessor {

    /**
     * Tier list on which the processor operates.
     */
    private lateinit var tierList: TierList

    /**
     * Resource image that represents a drag target.
     */
    private val targetImage by lazy {
        ResourceImage(resId = R.drawable.ic_crop_free)
    }

    /**
     * Attaches tier list to the processor.
     *
     * It is required to set tier list before calling [processDragEffect].
     *
     * @param tierList Tier list to set.
     */
    override fun setTierList(tierList: TierList) {
        this.tierList = tierList
    }

    /**
     * Converts drag effect to tier list event.
     *
     * It is required to [setTierList] before calling this function.
     *
     * @param effect Drag effect to process.
     * @return Resulting tier list event.
     */
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
     * Converts highlight effect to tier list event.
     *
     * Unless the effect is [HighlightTrashBin], additionally inserts [targetImage] into the target
     * position.
     *
     * @param effect Highlight effect to process.
     * @receiver Tier list that consumes the effect.
     * @return Resulting tier list event.
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
     * Converts insert effect to tier list event.
     *
     * Additionally inserts the image into the tier list.
     *
     * @param effect Insert effect to process.
     * @receiver Tier list that consumes the effect.
     * @return Resulting tier list event.
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
     * Converts remove effect to tier list event.
     *
     * Unless the effect is [UnhighlightTrashBin], additionally removes the target image.
     *
     * @param effect Remove effect to process.
     * @receiver Tier list that consumes the effect.
     * @return Resulting tier list event.
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
     * Converts update effect to tier list event.
     *
     * Unless the effect is [ThrowToTrashBin], additionally updates the image in the tier list.
     *
     * @param effect Update effect to process.
     * @receiver Tier list that consumes the effect.
     * @return Resulting tier list event.
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
            is ThrowToTrashBin -> ImageRemoved(effect.image)
        }
    }
}