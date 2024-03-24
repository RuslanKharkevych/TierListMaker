package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * Base class that represents the effect of updating an item in a tier list.
 *
 * Update effects are produced when an image is dropped into a drag target.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class UpdateEffect : DragEffect() {

    /**
     * Factory for creating update effects.
     *
     * This should be the only place where instances of [UpdateEffect] subclasses are instantiated.
     */
    companion object Factory {

        /**
         * Creates update effect from shadow and target.
         *
         * @param shadow Image data of the shadow.
         * @param target Data of the drag target.
         * @return Created update effect.
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
                is TrashBinDragData -> ThrowToTrashBin(shadow.image)
            }
        }
    }
}

/**
 * Update effect of the image in the backlog.
 *
 * This effect is produced when user drops an image into the drag target that is a backlog image.
 *
 * @property data Image data to update.
 * @constructor Creates the effect from backlog image drag data.
 */
data class UpdateInBacklog(val data: ImageDragData) : UpdateEffect()

/**
 * Update effect of the image in a tier.
 *
 * This effect is produced when user drops an image into the drag target that is a tier image.
 *
 * @property data Image data to update.
 * @constructor Creates the effect from tier image drag data.
 */
data class UpdateInTier(val data: ImageDragData) : UpdateEffect()

/**
 * Update effect of the last image in the backlog.
 *
 * This effect is produced when user drops an image into the drag target that is the last image in
 * backlog.
 *
 * @property image Image to update.
 * @constructor Creates the effect from backlog image.
 */
data class UpdateLastInBacklog(val image: Image) : UpdateEffect()

/**
 * Update effect of the last image in a tier.
 *
 * This effect is produced when user drops an image into the drag target that is the last image in a
 * tier.
 *
 * @property image Image to update.
 * @property tierPosition Position of the tier.
 * @constructor Creates the effect from image at tier position.
 */
data class UpdateLastInTier(val image: Image, val tierPosition: Int) : UpdateEffect()

/**
 * Update effect of the trash bin.
 *
 * This effect is produced when user drops an image into the trash bin.
 *
 * @property image Image thrown to the trash bin.
 * @constructor Creates the effect from image.
 */
data class ThrowToTrashBin(val image: Image) : UpdateEffect()