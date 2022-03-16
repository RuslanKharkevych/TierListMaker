package me.khruslan.tierlistmaker.data.drag.actions

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.tierlist.Image

sealed class UpdateAction : DragAction() {
    companion object Factory {
        fun create(shadow: ImageDragData, target: DragData) = when (target) {
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
            is TrashBinDragData -> throw IllegalArgumentException(
                String.format(
                    "Cannot create %s for target of type %s",
                    UpdateAction::class.qualifiedName,
                    TrashBinDragData::class.qualifiedName
                )
            )
        }
    }
}

class UpdateInBacklog(val data: ImageDragData) : UpdateAction()
class UpdateInTier(val data: ImageDragData) : UpdateAction()
class UpdateLastInBacklog(val image: Image) : UpdateAction()
class UpdateLastInTier(val image: Image, val tierPosition: Int) : UpdateAction()