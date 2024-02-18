package me.khruslan.tierlistmaker.data.models.tierlist

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import java.util.*

/**
 * Tier is the rank of the [TierList] that contains header and images.
 *
 * A new empty tier can be created with default arguments but its style must be updated afterwards.
 * Note that this class is mutable. It can be stored in the database or passed as a navigation
 * argument.
 *
 * @property id Unique identifier of the tier. The default value is random UUID. This property can't
 * be changed.
 * @property images List of tier images. The default value is empty list.
 * @property style Style of the tier header. The default value is dummy and must be updated.
 * @constructor Creates the tier with provided id, images and style.
 */
@Parcelize
data class Tier(
    val id: String = UUID.randomUUID().toString(),
    val images: MutableList<Image> = mutableListOf(),
    var style: TierStyle = TierStyle()
) : Parcelable

/**
 * Style of the [Tier] includes its title and color.
 *
 * The style is applied to the tier header only and does not affect images inside the tier. A new
 * style can be created with default parameters, but those are dummies. It's useful when the style
 * is not known in advance and will be updated afterwards. This class is immutable. It can be
 * stored in the database or passed as a navigation argument.
 *
 * @property title Title of the tier. The default value is an empty string.
 * @property color Color of the tier header. The default value is transparent.
 * @constructor Creates the tier style with provided title and color.
 */
@Parcelize
data class TierStyle(
    val title: String = "",
    @ColorInt val color: Int = Color.TRANSPARENT
) : Parcelable