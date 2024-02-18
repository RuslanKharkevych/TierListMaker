package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * Base class that represents the effect of inserting the image data into a tier list.
 *
 * Insert effects are usually produced when drag images are restored after drag has ended. Besides
 * that, they can also be produced when drag images are dropped into the target. This could happen
 * if the drop is too fast for the highlight effect to take place.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class InsertEffect : DragEffect() {

    /**
     * Factory for creating insert effects.
     *
     * This should be the only place where instances of [InsertEffect] subclasses are instantiated.
     */
    companion object Factory {

        /**
         * Creates insert effect from the image drag data.
         *
         * Used for creating effects as a result of restoring the image in the tier list.
         *
         * @param data Image data to restore.
         * @return Created insert effect.
         */
        fun create(data: ImageDragData): InsertEffect {
            return if (data.isBacklogImage) {
                InsertToBacklog(data)
            } else {
                InsertToTier(data)
            }
        }

        /**
         * Creates insert effect from shadow and target.
         *
         * Used for creating effects as a result of inserting the image into specific target. Note
         * that it's impossible to insert into the trash bin because [ThrowToTrashBin] is an
         * update effect.
         *
         * @param shadow Image data to insert.
         * @param target Data of the target.
         * @throws [IllegalArgumentException] If target is [TrashBinDragData].
         * @return Created insert effect.
         */
        fun create(shadow: ImageDragData, target: DragData): InsertEffect {
            return when (target) {
                is ImageDragData -> create(target.copy(image = shadow.image))
                is TierDragData -> if (target.isBacklog) {
                    InsertToEndOfBacklog(shadow.image)
                } else {
                    InsertToEndOfTier(
                        image = shadow.image,
                        tierPosition = target.tierPosition
                    )
                }
                is TrashBinDragData -> throw IllegalArgumentException(
                    "Cannot create InsertEffect for TrashBinDragData"
                )
            }
        }
    }
}

/**
 * Insert effect of the image at given position in backlog.
 *
 * This effect is produced when image is restored or dropped into the backlog.
 *
 * @property data Image data to insert.
 * @constructor Creates the effect from backlog image drag data.
 */
data class InsertToBacklog(val data: ImageDragData) : InsertEffect()

/**
 * Insert effect of the image at given position in tier.
 *
 * This effect is produced when image is restored or dropped into a tier.
 *
 * @property data Image data to insert.
 * @constructor Creates the effect from tier image drag data.
 */
data class InsertToTier(val data: ImageDragData) : InsertEffect()

/**
 * Insert effect of the image at the end of the backlog.
 *
 * This effect is produced when image is dropped into the spot in backlog where there are no images.
 *
 * @property image Image to insert.
 * @constructor Creates the effect from backlog image.
 */
data class InsertToEndOfBacklog(val image: Image) : InsertEffect()

/**
 * Insert effect of the image at the end of the tier.
 *
 * This effect is produced when image is dropped into the spot in tier where there are no images.
 *
 * @property image Image to insert.
 * @property tierPosition Position of the tier.
 * @constructor Creates the effect from image at tier position.
 */
data class InsertToEndOfTier(val image: Image, val tierPosition: Int) : InsertEffect()