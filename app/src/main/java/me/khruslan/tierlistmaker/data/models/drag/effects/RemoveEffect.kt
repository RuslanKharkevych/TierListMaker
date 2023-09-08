package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData

/**
 * [DragEffect] implementation for remove effects.
 * Base class that represents an effect of removing an item from a tier list.
 *
 * @see RemoveFromTier
 * @see RemoveFromBacklog
 * @see RemoveLastFromTier
 * @see RemoveLastFromBacklog
 * @see UnhighlightTrashBin
 */
sealed class RemoveEffect : DragEffect() {

    /**
     * Factory for creating [RemoveEffect].
     */
    companion object Factory {

        /**
         * Creates [RemoveEffect] based on [DragData].
         *
         * @param data data to remove.
         * @return created [RemoveEffect].
         */
        fun create(data: DragData): RemoveEffect {
            return when (data) {
                is ImageDragData -> if (data.isBacklogImage) {
                    RemoveFromBacklog(data.itemPosition)
                } else {
                    RemoveFromTier(
                        itemPosition = data.itemPosition,
                        tierPosition = data.tierPosition
                    )
                }
                is TierDragData -> if (data.isBacklog) {
                    RemoveLastFromBacklog
                } else {
                    RemoveLastFromTier(data.tierPosition)
                }
                is TrashBinDragData -> UnhighlightTrashBin
            }
        }
    }
}

/**
 * [RemoveEffect] implementation that removes item from tier at given position.
 *
 * @property itemPosition position of the item in a tier.
 * @property tierPosition position of the tier in a tier list.
 */
data class RemoveFromTier(val itemPosition: Int, val tierPosition: Int) : RemoveEffect()

/**
 * [RemoveEffect] implementation that removes item from backlog at given position.
 *
 * @property itemPosition position of the item in the backlog.
 */
data class RemoveFromBacklog(val itemPosition: Int) : RemoveEffect()

/**
 * [RemoveEffect] implementation that removes the last item from tier at given position.
 *
 * @property tierPosition position of the tier.
 */
data class RemoveLastFromTier(val tierPosition: Int) : RemoveEffect()

/**
 * [RemoveEffect] implementation that removes the last item from backlog.
 */
data object RemoveLastFromBacklog : RemoveEffect()

/**
 * [RemoveEffect] implementation that removes the highlight from the trash bin.
 */
data object UnhighlightTrashBin : RemoveEffect()