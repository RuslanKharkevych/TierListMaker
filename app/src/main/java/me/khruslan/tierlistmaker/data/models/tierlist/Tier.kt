package me.khruslan.tierlistmaker.data.models.tierlist

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import java.util.*

/**
 * Data that represents single tier in a tier list.
 *
 * @property id unique identifier of the tier.
 * @property images list of tier images.
 * @property style style of the tier.
 */
@Parcelize
data class Tier(
    val id: String = UUID.randomUUID().toString(),
    val images: MutableList<Image> = mutableListOf(),
    var style: TierStyle = TierStyle()
) : Parcelable

/**
 * Data that represents the style of the tier list.
 *
 * @property title title of the tier list.
 * @property color color of the tier list.
 */
@Parcelize
data class TierStyle(
    val title: String = "",
    @ColorInt val color: Int = Color.TRANSPARENT
) : Parcelable