package me.khruslan.tierlistmaker.data.tierlist

import androidx.annotation.ColorInt

data class Tier(
    val title: String,
    @ColorInt val color: Int,
    val imageUrls: MutableList<String?>
)