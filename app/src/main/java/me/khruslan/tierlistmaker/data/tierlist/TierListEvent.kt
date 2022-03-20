package me.khruslan.tierlistmaker.data.tierlist

sealed class TierListEvent

object BacklogDataChanged : TierListEvent()
object BacklogImagesAdded : TierListEvent()
data class BacklogItemChanged(val itemPosition: Int) : TierListEvent()
data class BacklogItemInserted(val itemPosition: Int) : TierListEvent()

object TierListChanged : TierListEvent()
data class TierChanged(val tierPosition: Int) : TierListEvent()
data class TierInserted(val tierPosition: Int) : TierListEvent()

data class ImageSizeUpdated(val imageSize: Int) : TierListEvent()