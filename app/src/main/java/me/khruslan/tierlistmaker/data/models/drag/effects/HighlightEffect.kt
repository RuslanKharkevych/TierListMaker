package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData

/**
 * [DragEffect] implementation for highlight effects.
 * Base class that represents the effect of the current drag location.
 *
 * @see HighlightInBacklog
 * @see HighlightInTier
 * @see HighlightLastInBacklog
 * @see HighlightLastInTier
 * @see HighlightTrashBin
 */
sealed class HighlightEffect : DragEffect() {

    /**
     * Factory for creating [HighlightEffect].
     */
    companion object Factory {

        /**
         * Creates [HighlightEffect] based on [DragData] of the target.
         *
         * @param target data of current drag location.
         * @return created [HighlightEffect].
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
 * [HighlightEffect] implementation that highlights the item in the tier at given position.
 *
 * @property itemPosition position of the item in a tier.
 * @property tierPosition position of the tier in a tier list.
 */
data class HighlightInTier(val itemPosition: Int, val tierPosition: Int) : HighlightEffect()

/**
 * [HighlightEffect] implementation that highlights the item in the backlog at given position.
 *
 * @property itemPosition position of the item in the backlog.
 */
data class HighlightInBacklog(val itemPosition: Int) : HighlightEffect()

/**
 * [HighlightEffect] implementation that highlights the last item in the tier at given position.
 *
 * @property tierPosition position of the tier in a tier list.
 */
data class HighlightLastInTier(val tierPosition: Int) : HighlightEffect()

/**
 * [HighlightEffect] implementation that highlights the last item in the backlog.
 */
data object HighlightLastInBacklog : HighlightEffect()

/**
 * [HighlightEffect] implementation that highlights a trash bin.
 */
data object HighlightTrashBin : HighlightEffect()
