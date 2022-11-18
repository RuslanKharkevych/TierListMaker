package me.khruslan.tierlistmaker.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * [DragEffect] implementation for insert effects.
 * Base class that represents an effect of inserting the image data into a tier list.
 *
 * @see InsertToBacklog
 * @see InsertToTier
 * @see InsertToEndOfBacklog
 * @see InsertToEndOfTier
 * @see InsertToTrashBin
 */
sealed class InsertEffect : DragEffect() {

    /**
     * Factory for creating [InsertEffect].
     */
    companion object Factory {

        /**
         * Creates [InsertEffect] based on [ImageDragData].
         * Used for restoring the image in a tier list.
         *
         * @param data image data to restore.
         * @return created [InsertEffect] of either [InsertToBacklog] or [InsertToTier] type.
         */
        fun create(data: ImageDragData): InsertEffect {
            return if (data.isBacklogImage) {
                InsertToBacklog(data)
            } else {
                InsertToTier(data)
            }
        }

        /**
         * Creates [InsertEffect] based on image data and target.
         * Used to create an effect of inserting image data into specific target.
         *
         * @param shadow image data to insert.
         * @param target data of the target.
         * @return created [InsertEffect].
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
                is TrashBinDragData -> InsertToTrashBin
            }
        }
    }
}

/**
 * [InsertEffect] implementation that inserts image into the backlog at given position.
 *
 * @property data image data to insert.
 */
data class InsertToBacklog(val data: ImageDragData) : InsertEffect()

/**
 * [InsertEffect] implementation that inserts image into the tier at given position.
 *
 * @property data image data to insert.
 */
data class InsertToTier(val data: ImageDragData) : InsertEffect()

/**
 * [InsertEffect] implementation that inserts image into the end of the backlog.
 *
 * @property image image to insert.
 */
data class InsertToEndOfBacklog(val image: Image) : InsertEffect()

/**
 * [InsertEffect] implementation that inserts image into the end of the tier at given position.
 *
 * @property image image to insert.
 * @property tierPosition position of the tier.
 */
data class InsertToEndOfTier(val image: Image, val tierPosition: Int) : InsertEffect()

/**
 * [InsertEffect] implementation that throws an image into the trash bin.
 */
object InsertToTrashBin : InsertEffect()