package me.khruslan.tierlistmaker.data.drag

import android.content.ClipData
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.extensions.toInt

sealed class DragData

data class ImageDragData(
    val image: Image,
    val itemPosition: Int,
    val tierPosition: Int
) : DragData() {
    val isBacklogImage = tierPosition == BACKLOG_TIER_POSITION

    companion object {
        const val LABEL = "me.khruslan.tierlistmaker.data.drag.ImageDragData"

        fun fromClipData(clipData: ClipData) = ImageDragData(
            image = Image.fromPayload(
                id = clipData.getItemAt(0).text.toString(),
                payload = clipData.getItemAt(1).text.toString()
            ),
            itemPosition = clipData.getItemAt(2).text.toInt(),
            tierPosition = clipData.getItemAt(3).text.toInt()
        )
    }

    fun toClipData(): ClipData = ClipData.newPlainText(LABEL, image.id).apply {
        addItem(ClipData.Item(image.payload))
        addItem(ClipData.Item(itemPosition.toString()))
        addItem(ClipData.Item(tierPosition.toString()))
    }
}

data class TierDragData(val tierPosition: Int) : DragData() {
    val isBacklog = tierPosition == BACKLOG_TIER_POSITION
}

object TrashBinDragData : DragData()