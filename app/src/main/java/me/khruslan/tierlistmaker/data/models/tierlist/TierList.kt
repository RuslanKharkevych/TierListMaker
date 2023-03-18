package me.khruslan.tierlistmaker.data.models.tierlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * Data that represents the tier list.
 *
 * @property id unique identifier of the tier list.
 * @property title title of the tier list.
 * @property zoomValue max number of images (including tier view) displayed in a row.
 * @property tiers list of tiers.
 * @property backlogImages list of backlog images.
 */
@Parcelize
data class TierList(
    val id: String,
    var title: String,
    var zoomValue: Int,
    var tiers: MutableList<Tier>,
    val backlogImages: MutableList<Image>
) : Parcelable {

    /**
     * Companion object of [TierList] used for storing constants.
     */
    companion object {
        private const val PREVIEW_IMAGES_COUNT = 3
    }

    /**
     * [Preview] of this [TierList].
     */
    val preview get() = Preview(id, title, previewImages)

    /**
     * List of preview images for this [TierList] with max size of [PREVIEW_IMAGES_COUNT]
     */
    private val previewImages
        get() = tiers
            .flatMap { it.images }
            .plus(backlogImages)
            .take(PREVIEW_IMAGES_COUNT)

    /**
     * Data that represents preview of the [TierList].
     *
     * @property id unique identifier of the tier list.
     * @property title title of the tier list.
     * @property images list of preview images.
     */
    data class Preview(
        val id: String,
        val title: String,
        val images: List<Image>
    )
}