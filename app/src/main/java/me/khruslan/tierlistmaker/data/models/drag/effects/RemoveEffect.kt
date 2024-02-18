package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData

/**
 * Base class that represents an effect of removing an item from a tier list.
 *
 * Remove effects are produced when user starts drag or when drag target changes.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class RemoveEffect : DragEffect() {

    /**
     * Factory for creating remove effects.
     *
     * This should be the only place where instances of [RemoveEffect] subclasses are instantiated.
     */
    companion object Factory {

        /**
         * Creates remove effect from the drag data.
         *
         * All drag data types are acceptable.
         *
         * @param data Data to remove.
         * @return created remove effect.
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
 * Remove effect of the item at given position in tier.
 *
 * This effect is produced when user starts drag from tier or when drag target changes so that
 * tier image is no longer highlighted.
 *
 * @property itemPosition Position of the item in a tier.
 * @property tierPosition Position of the tier in a tier list.
 * @constructor Creates the effect from item position in the tier list.
 */
data class RemoveFromTier(val itemPosition: Int, val tierPosition: Int) : RemoveEffect()

/**
 * Remove effect of the item at given position in backlog.
 *
 * This effect is produced when user starts drag from backlog or when drag target changes so that
 * backlog image is no longer highlighted.
 *
 * @property itemPosition Position of the item in the backlog.
 * @constructor Creates the effect from item position in backlog.
 */
data class RemoveFromBacklog(val itemPosition: Int) : RemoveEffect()

/**
 * Remove effect of the last item in tier.
 *
 * This effect is produced when drag target changes so that the last image in tier is no longer
 * highlighted.
 *
 * @property tierPosition Position of the tier.
 * @constructor Creates the effect from tier position.
 */
data class RemoveLastFromTier(val tierPosition: Int) : RemoveEffect()

/**
 * Remove effect of the last item in backlog.
 *
 * This effect is produced when drag target changes so that the last image in backlog is no longer
 * highlighted.
 */
data object RemoveLastFromBacklog : RemoveEffect()

/**
 * Remove effect of the trash bin.
 *
 * This effect is produced when drag target changes so that trash bin is no longer highlighted.
 */
data object UnhighlightTrashBin : RemoveEffect()