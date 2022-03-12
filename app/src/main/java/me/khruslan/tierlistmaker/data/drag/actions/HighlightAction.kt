package me.khruslan.tierlistmaker.data.drag.actions

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData

sealed class HighlightAction : DragAction() {
    companion object Factory {
        fun create(target: DragData) = when (target) {
            is ImageDragData -> if (target.isBacklogImage) {
                HighlightInBacklog(target.itemPosition)
            } else {
                HighlightInTier(
                    itemPosition = target.itemPosition,
                    tierPosition = target.tierPosition
                )
            }
            is TierDragData -> if (target.isBacklog) {
                HighlightLastInBacklog
            } else {
                HighlightLastInTier(target.tierPosition)
            }
            is TrashBinDragData -> HighlightTrashBin
        }
    }
}

class HighlightInBacklog(val itemPosition: Int) : HighlightAction()
class HighlightInTier(val itemPosition: Int, val tierPosition: Int) : HighlightAction()
class HighlightLastInTier(val tierPosition: Int) : HighlightAction()
object HighlightLastInBacklog : HighlightAction()
object HighlightTrashBin : HighlightAction()
