package me.khruslan.tierlistmaker.presentation.utils

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

/**
 * Returns the color int for the provided theme color attribute.
 *
 * This extension is a shorthand of the [MaterialColors.getColor] function.
 *
 * @param colorAttrRes Color theme attribute.
 * @receiver Activity context.
 * @return Resolved color.
 * @throws [IllegalArgumentException] If the attribute is not set in the current theme.
 */
@ColorInt
fun Context.getMaterialColor(@AttrRes colorAttrRes: Int): Int {
    return MaterialColors.getColor(this, colorAttrRes, "Context.getMaterialColor")
}