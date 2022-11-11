package me.khruslan.tierlistmaker.utils.extensions

import android.content.Intent
import android.os.Build
import android.os.Parcelable

/**
 * A helper to get [Parcelable] extra from [Intent] that supports all versions.
 *
 * @param T type of the [Parcelable].
 * @param name name of the extra.
 * @return the value of the resolved item.
 */
inline fun <reified T: Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(name)
    }
}