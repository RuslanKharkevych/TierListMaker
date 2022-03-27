package me.khruslan.tierlistmaker.data.tierlist

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Tier(
    val id: String = UUID.randomUUID().toString(),
    val images: MutableList<Image> = mutableListOf(),
    var style: TierStyle = TierStyle()
) : Parcelable

@Parcelize
data class TierStyle(
    val title: String = "",
    @ColorInt val color: Int = Color.TRANSPARENT,
) : Parcelable