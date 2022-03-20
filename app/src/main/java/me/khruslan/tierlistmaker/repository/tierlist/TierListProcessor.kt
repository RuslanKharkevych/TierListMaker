package me.khruslan.tierlistmaker.repository.tierlist

import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.tierlist.BacklogDataChanged
import me.khruslan.tierlistmaker.data.tierlist.BacklogItemInserted
import me.khruslan.tierlistmaker.data.tierlist.TierChanged
import me.khruslan.tierlistmaker.data.drag.actions.*
import me.khruslan.tierlistmaker.data.tierlist.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.utils.extensions.updateLast

class TierListProcessor {
    private lateinit var tierList: TierList

    private val targetImage by lazy {
        ResourceImage(resId = R.drawable.ic_crop_free)
    }

    fun setTierList(tierList: TierList) {
        this.tierList = tierList
    }

    fun processDragAction(action: DragAction) = when (action) {
        is HighlightAction -> tierList.processHighlightAction(action)
        is InsertAction -> tierList.processInsertAction(action)
        is RemoveAction -> tierList.processRemoveAction(action)
        is UpdateAction -> tierList.processUpdateAction(action)
    }

    private fun TierList.processHighlightAction(action: HighlightAction) = when (action) {
        is HighlightInBacklog -> {
            backlogImages.add(action.itemPosition, targetImage)
            BacklogItemInserted(action.itemPosition)
        }
        is HighlightInTier -> {
            tiers[action.tierPosition].images.add(action.itemPosition, targetImage)
            TierChanged(action.tierPosition)
        }
        is HighlightLastInTier -> {
            tiers[action.tierPosition].images += targetImage
            TierChanged(action.tierPosition)
        }
        is HighlightLastInBacklog -> {
            backlogImages += targetImage
            BacklogItemInserted(backlogImages.lastIndex)
        }
        is HighlightTrashBin -> TODO("Highlight trash bin")
    }

    private fun TierList.processInsertAction(action: InsertAction) = when (action) {
        is InsertToBacklog -> {
            backlogImages.add(action.data.itemPosition, action.data.image)
            BacklogItemInserted(action.data.itemPosition)
        }
        is InsertToTier -> {
            tiers[action.data.tierPosition].images.add(action.data.itemPosition, action.data.image)
            TierChanged(action.data.tierPosition)
        }
        is InsertToEndOfBacklog -> {
            backlogImages += action.image
            BacklogItemInserted(backlogImages.lastIndex)
        }
        is InsertToEndOfTier -> {
            tiers[action.tierPosition].images += action.image
            TierChanged(action.tierPosition)
        }
        is InsertToTrashBin -> TODO("Remove image")
    }

    private fun TierList.processRemoveAction(action: RemoveAction) = when (action) {
        is RemoveFromBacklog -> {
            backlogImages.removeAt(action.itemPosition)
            BacklogDataChanged
        }
        is RemoveFromTier -> {
            tiers[action.tierPosition].images.removeAt(action.itemPosition)
            TierChanged(action.tierPosition)
        }
        is RemoveLastFromBacklog -> {
            backlogImages.removeLast()
            BacklogDataChanged
        }
        is RemoveLastFromTier -> {
            tiers[action.tierPosition].images.removeLast()
            TierChanged(action.tierPosition)
        }
        is UnhighlightTrashBin -> TODO("Unhighlight trash bin")
    }

    private fun TierList.processUpdateAction(action: UpdateAction) = when (action) {
        is UpdateInBacklog -> {
            backlogImages[action.data.itemPosition] = action.data.image
            BacklogDataChanged
        }
        is UpdateInTier -> {
            tiers[action.data.tierPosition].images[action.data.itemPosition] = action.data.image
            TierChanged(action.data.tierPosition)
        }
        is UpdateLastInBacklog -> {
            backlogImages.updateLast(action.image)
            BacklogDataChanged
        }
        is UpdateLastInTier -> {
            tiers[action.tierPosition].images.updateLast(action.image)
            TierChanged(action.tierPosition)
        }
    }
}