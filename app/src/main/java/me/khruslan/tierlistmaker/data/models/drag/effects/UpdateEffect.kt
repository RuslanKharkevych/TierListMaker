package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * [DragEffect] implementation for update effects.
 * Base class that represents the effect of updating an image in a tier list.
 *
 * @see UpdateInBacklog
 * @see UpdateInTier
 * @see UpdateLastInBacklog
 * @see UpdateLastInTier
 * @see ThrowToTrashBin
 */
sealed class UpdateEffect : DragEffect() {

    /**
     * Factory for creating [UpdateEffect].
     */
    companion object Factory {

        /**
         * Creates [UpdateEffect] based on shadow and target.
         *
         * @param shadow data of the updated image.
         * @param target data of the target to update.
         * @return created [UpdateEffect].
         */
        fun create(shadow: ImageDragData, target: DragData): UpdateEffect {
            return when (target) {
                is ImageDragData -> {
                    val data = target.copy(image = shadow.image)
                    if (target.isBacklogImage) UpdateInBacklog(data) else UpdateInTier(data)
                }
                is TierDragData -> if (target.isBacklog) {
                    UpdateLastInBacklog(shadow.image)
                } else {
                    UpdateLastInTier(
                        image = shadow.image,
                        tierPosition = target.tierPosition
                    )
                }
                is TrashBinDragData -> ThrowToTrashBin
            }
        }
    }
}

/**
 * [UpdateEffect] implementation that updates the given image in the backlog.
 *
 * @property data image data to update.
 */
data class UpdateInBacklog(val data: ImageDragData) : UpdateEffect()

/**
 * [UpdateEffect] implementation that updates the given image in the tier.
 *
 * @property data image data to update.
 */
data class UpdateInTier(val data: ImageDragData) : UpdateEffect()

/**
 * [UpdateEffect] implementation that updates the last image in the backlog.
 *
 * @property image image to update.
 */
data class UpdateLastInBacklog(val image: Image) : UpdateEffect()

/**
 * [UpdateEffect] implementation that updates the last image in the tier.
 *
 * @property image image to update.
 * @property tierPosition position of the tier.
 */
data class UpdateLastInTier(val image: Image, val tierPosition: Int) : UpdateEffect()

/**
 * [UpdateEffect] implementation that throws an image into the trash bin.
 */
data object ThrowToTrashBin : UpdateEffect()