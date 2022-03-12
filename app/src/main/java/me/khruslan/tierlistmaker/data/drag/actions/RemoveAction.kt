package me.khruslan.tierlistmaker.data.drag.actions

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData

sealed class RemoveAction : DragAction() {
    companion object Factory {
        fun create(data: DragData) = when (data) {
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

class RemoveFromBacklog(val itemPosition: Int) : RemoveAction()
class RemoveFromTier(val itemPosition: Int, val tierPosition: Int) : RemoveAction()
class RemoveLastFromTier(val tierPosition: Int) : RemoveAction()
object RemoveLastFromBacklog : RemoveAction()
object UnhighlightTrashBin : RemoveAction()