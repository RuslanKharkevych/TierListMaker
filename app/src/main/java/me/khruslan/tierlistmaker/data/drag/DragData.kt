package me.khruslan.tierlistmaker.data.drag

import android.content.ClipData
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.extensions.toInt

sealed class DragData

data class ImageDragData(
    val imageUrl: String?,
    val itemPosition: Int,
    val tierPosition: Int
) : DragData() {
    val isBacklogImage = tierPosition == BACKLOG_TIER_POSITION

    companion object {
        const val LABEL = "me.khruslan.tierlistmaker.data.drag.ImageDragData"

        fun fromClipData(clipData: ClipData) = ImageDragData(
            clipData.getItemAt(0).text.toString(),
            clipData.getItemAt(1).text.toInt(),
            clipData.getItemAt(2).text.toInt()
        )
    }

    fun toClipData(): ClipData = ClipData.newPlainText(LABEL, imageUrl).apply {
        addItem(ClipData.Item(itemPosition.toString()))
        addItem(ClipData.Item(tierPosition.toString()))
    }
}

data class TierDragData(val tierPosition: Int) : DragData() {
    val isBacklog = tierPosition == BACKLOG_TIER_POSITION
}

object TrashBinDragData : DragData()