package me.khruslan.tierlistmaker.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.TypedValue
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import java.util.Locale

/**
 * Display width of the device in pixels.
 *
 * @receiver Either activity or application [Context].
 */
val Context.displayWidthPixels get() = resources.displayMetrics.widthPixels

/**
 * Converts value from dp to pixels.
 *
 * @param value value in dp.
 * @receiver context for accessing display metrics.
 * @return value in pixels.
 */
fun Context.dpToPx(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

/**
 * Finds [NavHostFragment] by id.
 *
 * @param id id of the [NavHostFragment].
 * @receiver [FragmentActivity] that hosts [NavHostFragment].
 * @return The found [NavHostFragment].
 */
fun FragmentActivity.findNavHostFragmentById(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

/**
 * A helper to get [Parcelable] extra from [Intent] that supports all versions.
 *
 * @param T type of the [Parcelable].
 * @param name name of the extra.
 * @return the value of the resolved item.
 */
inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(name)
    }
}

/**
 * Swaps two items in the [MutableList].
 *
 * @param T type of the list item.
 * @param a first item.
 * @param b second item.
 * @receiver [MutableList] of an arbitrary type.
 */
fun <T> MutableList<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

/**
 * Sets the [value] to the last item in the [MutableList].
 *
 * @param T type of the list item.
 * @param value value to set.
 * @receiver [MutableList] of an arbitrary type.
 */
fun <T> MutableList<T>.updateLast(value: T) {
    this[lastIndex] = value
}

/**
 * Reads [String] from [Parcel]. Can be used instead of [Parcel.readString] to ensure that the
 * returned value is non-nullable.
 *
 * @receiver Any [Parcel].
 * @return Non-nullable [String].
 * @throws [IllegalStateException] if unable to read.
 */
fun Parcel.requireString(): String {
    return readString() ?: throw IllegalStateException("Can't read string from parcel $this")
}

/**
 * Returns a copy of the string with its first letter as a capital letter. Replacement for Kotlin's
 * deprecated [String.capitalize] function.
 *
 * @receiver string to capitalize.
 * @return capitalized string.
 */
fun String.capitalized(): String {
    return replaceFirstChar { char ->
        if (char.isLowerCase())
            char.titlecase(Locale.getDefault())
        else {
            char.toString()
        }
    }
}