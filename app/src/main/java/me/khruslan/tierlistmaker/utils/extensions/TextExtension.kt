package me.khruslan.tierlistmaker.utils.extensions

import kotlin.text.toInt as convertToInt

/**
 * Converts [CharSequence] to [Int].
 *
 * @receiver [CharSequence] that should be a valid representation of the decimal number.
 * @return Result of the conversion.
 */
fun CharSequence.toInt(): Int {
    return toString().convertToInt()
}