package me.khruslan.tierlistmaker.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.ui.adapters.reorderable.Reorderable
import me.khruslan.tierlistmaker.ui.adapters.reorderable.ReorderableCallback
import java.util.*

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
 * Loads the image from the device file system into the [ImageView] in the tier list.
 *
 * @param filePath full path to the file.
 * @receiver Any [ImageView].
 */
fun ImageView.loadTierListImage(filePath: String) {
    Glide.with(this)
        .load(filePath)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .into(this)
}

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
 * @throws [IllegalArgumentException] if unable to read.
 */
fun Parcel.requireString(): String {
    return readString() ?: throw IllegalStateException("Can't read string from parcel $this")
}

/**
 * Attaches [ItemTouchHelper] with [ReorderableCallback] to the [RecyclerView]. This function
 * requires that [RecyclerView] has set [RecyclerView.Adapter] that implements [Reorderable]
 * interface. Enables both changing the order of items and swipe-to-dismiss functionality.
 *
 * @receiver recycler view for which reordering will be enabled.
 */
fun RecyclerView.enableReordering() {
    val callback = ReorderableCallback(adapter as Reorderable)
    ItemTouchHelper(callback).attachToRecyclerView(this)
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