package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData

/**
 * Base class that represents the highlight effect of the current drag location.
 *
 * Highlight effects are produced when user hovers the drag shadow over views that are able to
 * receive drag events.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class HighlightEffect : DragEffect() {

    /**
     * Factory for creating highlight effects.
     *
     * This should be the only place where instances of [HighlightEffect] subclasses are
     * instantiated.
     */
    companion object Factory {

        /**
         * Creates highlight effect from the drag data of the target.
         *
         * All drag data types are acceptable.
         *
         * @param target Data of current drag location.
         * @return Created highlight effect.
         */
        fun create(target: DragData): HighlightEffect {
            return when (target) {
                is ImageDragData -> if (target.isBacklogImage) {
                    HighlightInBacklog(target.itemPosition)
                } else {
                    HighlightInTier(
                        itemPosition = target.itemPosition,
                        tierPosition = target.tierPosition
                    )
                }
                is TierDragData -> if (target.isBacklog) {
                    HighlightLastInBacklog
                } else {
                    HighlightLastInTier(target.tierPosition)
                }
                is TrashBinDragData -> HighlightTrashBin
            }
        }
    }
}

/**
 * Highlight effect of the item  at given position in tier.
 *
 * This effect is produced when user hovers the drag shadow over an image inside a tier.
 *
 * @property itemPosition Position of the item in a tier.
 * @property tierPosition Position of the tier in a tier list.
 * @constructor Creates the effect from the item position in tier list.
 */
data class HighlightInTier(val itemPosition: Int, val tierPosition: Int) : HighlightEffect()

/**
 * Highlight effect of the item at given position in backlog.
 *
 * This effect is produced when user hovers the drag shadow over an image inside the backlog.
 *
 * @property itemPosition Position of the item in the backlog.
 * @constructor Creates the effect from the item position in backlog.
 */
data class HighlightInBacklog(val itemPosition: Int) : HighlightEffect()

/**
 * Highlight effect of the last item in tier.
 *
 * This effect is produced when user hovers the drag shadow over a spot in tier where there are no
 * images.
 *
 * @property tierPosition Position of the tier in a tier list.
 * @constructor Creates the effect from the tier position.
 */
data class HighlightLastInTier(val tierPosition: Int) : HighlightEffect()

/**
 * Highlight effect of the last item in backlog.
 *
 * This effect is produced when user hovers the drag shadow over a spot in backlog where there are
 * no images.
 */
data object HighlightLastInBacklog : HighlightEffect()

/**
 * Highlight effect of the trash bin.
 *
 * This effect is produced when user hovers the drag shadow over the trash bin.
 */
data object HighlightTrashBin : HighlightEffect()
