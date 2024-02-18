package me.khruslan.tierlistmaker.data.models.tierlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * Tier list is a table that contains images grouped into tiers based on their rankings.
 *
 * Note that this class is mutable. It can be stored in the database or passed as a navigation
 * argument.
 *
 * @property id Unique identifier of the tier list. This property can't be changed.
 * @property title Title of the tier list.
 * @property zoomValue Maximum number of items (images plus tier header) that can fit in a row. For
 * example, if zoomValue is 5, that means that each tier can contain maximum of 4 images in a row.
 * If there are more images, they will be moved to the new row within the same tier.
 * @property tiers List of tiers.
 * @property backlogImages List of backlog images. Backlog is a special row that contains images,
 * which haven't been ranked yet.
 * @constructor Creates a new tier list.
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
     * Tier list constants for internal use.
     */
    private companion object Constants {

        /**
         * Maximum count of images displayed in the [Preview].
         */
        private const val PREVIEW_IMAGES_COUNT = 3
    }

    /**
     * Preview of this tier list.
     *
     * Note that this is a property without the backing field, meaning it is created every time
     * it is accessed.
     */
    val preview get() = Preview(id, title, previewImages)

    /**
     * List of preview images for this tier list.
     *
     * Preview images are sorted from the highest ranking tier to the lowest. If there aren't enough
     * images in tiers, the ones from the backlog will be picked up. Maximum count of preview images
     * equals to [PREVIEW_IMAGES_COUNT].
     */
    private val previewImages
        get() = tiers
            .flatMap { it.images }
            .plus(backlogImages)
            .take(PREVIEW_IMAGES_COUNT)

    /**
     * Preview of the tier list contains its title and the list of the highest ranked images.
     *
     * Previews are used in a collection of tier lists. Each tier list can be mapped to preview
     * using its [preview] property.
     *
     * @property id Unique identifier of the tier list.
     * @property title Title of the tier list.
     * @property images List of preview images.
     * @constructor Creates the new preview of the tier list.
     */
    data class Preview(
        val id: String,
        val title: String,
        val images: List<Image>
    )
}