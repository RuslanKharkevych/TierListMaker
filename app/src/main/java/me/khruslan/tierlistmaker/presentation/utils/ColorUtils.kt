package me.khruslan.tierlistmaker.presentation.utils

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

/**
 * Returns the color int for the provided theme color attribute.
 *
 * @param colorAttrRes color theme attribute.
 * @receiver activity context.
 * @return resolved color.
 * @throws [IllegalArgumentException] if the attribute is not set in the current theme.
 */
@ColorInt
fun Context.getMaterialColor(@AttrRes colorAttrRes: Int): Int {
    return MaterialColors.getColor(this, colorAttrRes, "Context.getMaterialColor")
}