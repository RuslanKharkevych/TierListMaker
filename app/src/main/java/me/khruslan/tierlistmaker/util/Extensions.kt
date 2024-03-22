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
import java.io.Serializable
import java.util.Locale

/**
 * Display width of the device in pixels.
 *
 * @receiver Either activity or application context.
 */
val Context.displayWidthPixels get() = resources.displayMetrics.widthPixels

/**
 * Converts value from dp to pixels.
 *
 * @param value Value in dp.
 * @receiver Context for accessing display metrics.
 * @return Value in pixels.
 */
fun Context.dpToPx(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

/**
 * Finds NavHostFragment by id.
 *
 * The fragment must be added to the fragment manager, otherwise the function will crash.
 *
 * @param id Id of the HavHostFragment.
 * @receiver FragmentActivity that hosts NavHostFragment.
 * @return The found NavHostFragment.
 */
fun FragmentActivity.findNavHostFragmentById(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

/**
 * A helper to get parcelable extra from intent that supports all versions.
 *
 * Starting from Android 13 uses type-safe overload of the [Intent.getParcelableExtra] function.
 *
 * @param T Type of the parcelable.
 * @param name Name of the extra.
 * @return The value of the resolved item.
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
 * A helper to get serializable extra from intent that supports all versions.
 *
 * Starting from Android 13 uses type-safe overload of the [Intent.getSerializableExtra] function.
 *
 * @param T Type of the serializable.
 * @param name Name of the extra.
 * @return The value of the resolved item.
 */
inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(name: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(name) as? T
    }
}

/**
 * Swaps two items in a mutable list.
 *
 * @param T Type of the list item.
 * @param a First item position.
 * @param b Second item position.
 * @receiver Mutable list of an arbitrary type.
 */
fun <T> MutableList<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

/**
 * Sets the value to the last item in a mutable list.
 *
 * Note that the list must not be empty, otherwise the function will crash.
 *
 * @param T Type of the list item.
 * @param value Value to set.
 * @receiver Mutable list of an arbitrary type.
 */
fun <T> MutableList<T>.updateLast(value: T) {
    this[lastIndex] = value
}

/**
 * Reads string from the parcel.
 *
 * Can be used instead of [Parcel.readString] to ensure that the returned value is non-nullable.
 *
 * @receiver A parcel that contains string at current data position.
 * @return Non-nullable string.
 * @throws [IllegalStateException] If unable to resolve a string.
 */
fun Parcel.requireString(): String {
    return readString() ?: throw IllegalStateException("Can't read string from parcel $this")
}

/**
 * Returns a copy of the string with its first letter as a capital letter.
 *
 * Replacement for Kotlin's deprecated [String.capitalize] function.
 *
 * @receiver String to capitalize.
 * @return Capitalized string.
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