package me.khruslan.tierlistmaker.utils.extensions

import android.os.Parcel
import androidx.lifecycle.SavedStateHandle
import io.paperdb.Book
import io.paperdb.Paper

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

/**
 * Reads value from [Book] by the [key].
 *
 * Can be used instead of [Book.read] to ensure that the returned value is non-nullable.
 *
 * @param T type of the value.
 * @param key key of the value.
 * @param defaultValue will be returned if key doesn't exist.
 * @receiver [Paper.book] instance.
 * @return Non-nullable value of type [T].
 * @throws [IllegalArgumentException] if [defaultValue] is **null**.
 */
fun <T> Book.readOrDefault(key: String, defaultValue: T) =
    read(key, defaultValue) ?: throw IllegalArgumentException("defaultValue can't be null")