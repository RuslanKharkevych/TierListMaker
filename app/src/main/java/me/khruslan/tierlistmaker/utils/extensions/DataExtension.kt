package me.khruslan.tierlistmaker.utils.extensions

import android.os.Parcel
import androidx.lifecycle.SavedStateHandle

/**
 * Reads [String] from [Parcel].
 *
 * Can be used instead of [Parcel.readString] to ensure that the returned value is non-nullable.
 *
 * @receiver Any [Parcel].
 * @return Non-nullable [String].
 * @throws [IllegalArgumentException] if unable to read.
 */
fun Parcel.requireString(): String {
    return readString() ?: throw IllegalStateException("Can't read string from parcel $this")
}

/**
 * Gets value from the [SavedStateHandle] by the [key].
 *
 * Can be used instead of [SavedStateHandle.get] to ensure that returned value is non-nullable.
 *
 * @param T type of the value.
 * @param key key of the value.
 * @receiver [SavedStateHandle] that contains [key].
 * @return Non-nullable value of type [T].
 * @throws IllegalArgumentException if [SavedStateHandle] doesn't contain [key].
 */
fun <T> SavedStateHandle.require(key: String): T {
    return get<T>(key)
        ?: throw IllegalArgumentException("SavedStateHandle $this doesn't contain key $key")
}
