package me.khruslan.tierlistmaker.data.tierlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TierList(
    val id: String,
    val title: String,
    var zoomValue: Int,
    var tiers: MutableList<Tier>,
    val backlogImages: MutableList<Image>
) : Parcelable {
    val preview get() = TierListPreview(id, title, previewImages)

    private val previewImages
        get() = tiers
            .flatMap { it.images }
            .plus(backlogImages)
            .take(TierListPreview.IMAGES_COUNT)
}