package me.khruslan.tierlistmaker.data.drag.actions

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData

sealed class UpdateAction : DragAction() {
    companion object Factory {
        fun create(shadow: ImageDragData, target: DragData) = when (target) {
            is ImageDragData -> {
                val data = target.copy(imageUrl = shadow.imageUrl)
                if (target.isBacklogImage) UpdateInBacklog(data) else UpdateInTier(data)
            }
            is TierDragData -> if (target.isBacklog) {
                UpdateLastInBacklog(shadow.imageUrl)
            } else {
                UpdateLastInTier(
                    imageUrl = shadow.imageUrl,
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
class UpdateLastInBacklog(val imageUrl: String?) : UpdateAction()
class UpdateLastInTier(val imageUrl: String?, val tierPosition: Int) : UpdateAction()