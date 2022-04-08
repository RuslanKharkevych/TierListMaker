package me.khruslan.tierlistmaker.utils.extensions

import android.content.Context

/**
 * Display width of the device in pixels.
 *
 * @receiver Either activity or application [Context].
 */
val Context.displayWidthPixels get() = resources.displayMetrics.widthPixels