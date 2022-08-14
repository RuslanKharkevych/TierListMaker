package me.khruslan.tierlistmaker.data.drag

import android.content.ClipData
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.extensions.toInt

/**
 * Base class that contains the data of a drag event.
 * Used to represent both shadow and target data.
 *
 * @see ImageDragData
 * @see TierDragData
 * @see TrashBinDragData
 */
sealed class DragData

/**
 * [DragData] implementation of the tier list image.
 *
 * @property image tier list image.
 * @property itemPosition position of the item in tier or backlog.
 * @property tierPosition position of the tier or [BACKLOG_TIER_POSITION] if image's in the backlog.
 */
data class ImageDragData(
    val image: Image,
    val itemPosition: Int,
    val tierPosition: Int
) : DragData() {

    /**
     * Indicates whether this drag data represents a backlog image.
     */
    val isBacklogImage = tierPosition == BACKLOG_TIER_POSITION

    /**
     * Companion object for creating [ImageDragData] from [ClipData].
     */
    companion object {

        /**
         * The label of [ClipData] created from an object of [ImageDragData] type.
         */
        const val LABEL = "me.khruslan.tierlistmaker.data.drag.ImageDragData"

        /**
         * Creates [ImageDragData] from [ClipData].
         * Use [toClipData] function for the opposite conversion.
         *
         * @param clipData [ClipData] object mapped from [ImageDragData].
         * @return created [ImageDragData].
         */
        fun fromClipData(clipData: ClipData): ImageDragData {
            return ImageDragData(
                image = Image.fromPayload(
                    id = clipData.getItemAt(0).text.toString(),
                    payload = clipData.getItemAt(1).text.toString()
                ),
                itemPosition = clipData.getItemAt(2).text.toInt(),
                tierPosition = clipData.getItemAt(3).text.toInt()
            )
        }
    }

    /**
     * Maps [ImageDragData] to [ClipData].
     * Use [fromClipData] for the opposite conversion.
     *
     * @return mapped [ClipData].
     */
    fun toClipData(): ClipData {
        return ClipData.newPlainText(LABEL, image.id).apply {
            addItem(ClipData.Item(image.payload))
            addItem(ClipData.Item(itemPosition.toString()))
            addItem(ClipData.Item(tierPosition.toString()))
        }
    }
}

/**
 * [DragData] implementation of the tier or backlog.
 *
 * @property tierPosition position of the tier or [BACKLOG_TIER_POSITION] in case of the backlog.
 */
data class TierDragData(val tierPosition: Int) : DragData() {

    /**
     * Indicates whether this drag data represents a backlog.
     */
    val isBacklog = tierPosition == BACKLOG_TIER_POSITION
}

/**
 * [DragData] implementation of the trash bin.
 */
object TrashBinDragData : DragData()