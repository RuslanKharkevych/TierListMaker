package me.khruslan.tierlistmaker.data.drag.actions

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.tierlist.Image

sealed class InsertAction : DragAction() {
    companion object Factory {
        fun create(data: ImageDragData) = if (data.isBacklogImage) {
            InsertToBacklog(data)
        } else {
            InsertToTier(data)
        }

        fun create(shadow: ImageDragData, target: DragData) = when (target) {
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

class InsertToBacklog(val data: ImageDragData) : InsertAction()
class InsertToTier(val data: ImageDragData) : InsertAction()
class InsertToEndOfBacklog(val image: Image) : InsertAction()
class InsertToEndOfTier(val image: Image, val tierPosition: Int) : InsertAction()
object InsertToTrashBin : InsertAction()