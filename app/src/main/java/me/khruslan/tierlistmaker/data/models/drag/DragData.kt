package me.khruslan.tierlistmaker.data.models.drag

import android.content.ClipData
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION

/**
 * Base class that contains the data of various drag items.
 *
 * Used to represent both shadows and targets.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class DragData

/**
 * Drag data of the tier list image.
 *
 * Can be used for both shadow and target inside a tier or backlog.
 *
 * @property image Tier list image.
 * @property itemPosition Position of the item in tier or backlog.
 * @property tierPosition Position of the tier or [BACKLOG_TIER_POSITION] if image's in the backlog.
 * @constructor Creates image drag data from image and its position in tier list.
 */
data class ImageDragData(
    val image: Image,
    val itemPosition: Int,
    val tierPosition: Int
) : DragData() {

    /**
     * Converts [ClipData] to [ImageDragData].
     */
    companion object Mapper {

        /**
         * The label of [ClipData] created from an object of [ImageDragData] type.
         */
        const val LABEL = "me.khruslan.tierlistmaker.data.models.drag.ImageDragData"

        /**
         * Creates image drag data from the clip data.
         *
         * Use [toClipData] function for the opposite conversion.
         *
         * @param clipData Clip data object that contains image drag data.
         * @return Created image drag data.
         */
        fun fromClipData(clipData: ClipData): ImageDragData {
            return ImageDragData(
                image = Image.fromPayload(
                    id = clipData.getItemAt(0).text.toString(),
                    payload = clipData.getItemAt(1).text.toString()
                ),
                itemPosition = clipData.getItemAt(2).text.toString().toInt(),
                tierPosition = clipData.getItemAt(3).text.toString().toInt()
            )
        }
    }

    /**
     * Indicates whether this drag data represents a backlog image.
     */
    val isBacklogImage = tierPosition == BACKLOG_TIER_POSITION

    /**
     * Maps image drag data to the clip data.
     *
     * Use [fromClipData] function for the opposite conversion.
     *
     * @return Clip data that contains image drag data.
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
 * Drag data of the tier target.
 *
 * In addition to tiers, it can also be used as a drag target of the backlog.
 *
 * @property tierPosition Position of the tier or [BACKLOG_TIER_POSITION] in case of the backlog.
 * @constructor Creates tier drag data from tier position.
 */
data class TierDragData(val tierPosition: Int) : DragData() {

    /**
     * Indicates whether this drag data represents a backlog.
     */
    val isBacklog = tierPosition == BACKLOG_TIER_POSITION
}

/**
 * Drag data of the trash bin.
 *
 * Used as a specific drag target type not directly related to the [TierList]. Suitable for removing
 * images from the tier list.
 */
data object TrashBinDragData : DragData()