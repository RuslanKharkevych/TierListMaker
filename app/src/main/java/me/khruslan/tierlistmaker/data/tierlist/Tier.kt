package me.khruslan.tierlistmaker.data.tierlist

import android.graphics.Color
import androidx.annotation.ColorInt
import java.util.*

data class Tier(
    val id: String = UUID.randomUUID().toString(),
    val images: MutableList<Image> = mutableListOf(),
    var style: TierStyle = TierStyle()
)

data class TierStyle(
    val title: String = "",
    @ColorInt val color: Int = Color.TRANSPARENT,
)