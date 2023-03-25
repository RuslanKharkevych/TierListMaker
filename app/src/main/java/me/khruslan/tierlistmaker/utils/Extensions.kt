package me.khruslan.tierlistmaker.utils

import android.animation.Animator
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.animation.doOnEnd
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.ui.adapters.reorderable.Reorderable
import me.khruslan.tierlistmaker.ui.adapters.reorderable.ReorderableCallback
import java.util.*
import kotlin.text.toInt as convertToInt

/**
 * Sets activity result with result code as [Activity.RESULT_OK] and data as [data].
 *
 * After that immediately finishes the activity.
 *
 * @receiver Any [Activity].
 * @param data [Intent] that is sent as an activity result.
 */
fun Activity.setResultDataAndFinish(data: Intent) {
    setResult(Activity.RESULT_OK, data)
    finish()
}

/**
 * Adds an action that will be invoked when the animation has ended. Same as [doOnEnd] but returns
 * [Animator] instead of [Animator.AnimatorListener] to allow using it in a builder.
 *
 * @param action action that should be invoked.
 * @return same [Animator] instance that was used as extension receiver.
 */
inline fun Animator.addOnEndAction(crossinline action: (Animator) -> Unit): Animator {
    doOnEnd(action)
    return this
}

/**
 * Converts [CharSequence] to [Int].
 *
 * @receiver [CharSequence] that should be a valid representation of the decimal number.
 * @return Result of the conversion.
 */
fun CharSequence.toInt(): Int {
    return toString().convertToInt()
}

/**
 * Display width of the device in pixels.
 *
 * @receiver Either activity or application [Context].
 */
val Context.displayWidthPixels get() = resources.displayMetrics.widthPixels

/**
 * Copies text to clipboard. Starting from [Build.VERSION_CODES.TIRAMISU] the system shows a default
 * UI to users when text is copied. On older devices a custom toast is shown.
 *
 * @param text text to copy.
 */
fun Context.copyTextToClipboard(text: String) {
    val clipboardService = getSystemService(Context.CLIPBOARD_SERVICE)
    val clipboardManager = clipboardService as? ClipboardManager ?: return
    val clip = ClipData.newPlainText(null, text)
    clipboardManager.setPrimaryClip(clip)

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        Toast.makeText(
            this,
            getString(R.string.toast_msg_text_copied, text),
            Toast.LENGTH_SHORT
        ).show()
    }
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
 * Get the preferred language of the device.
 *
 * @receiver resources obtained from [Resources.getSystem].
 * @return the language code.
 */
val Resources.deviceLanguage: String
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        configuration.locales[0].language
    } else {
        @Suppress("DEPRECATION")
        configuration.locale.language
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

/**
 * Requests to hide the soft input window.
 *
 * @receiver view attached to the window that is currently accepting input.
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * A helper function to start drag that supports all versions. Starting from [Build.VERSION_CODES.N]
 * the shadow will be fully opaque.
 *
 * @param data data of the image that will be dragged.
 * @receiver [View] that has [View.OnDragListener] set.
 * @return whether the drag was started successfully.
 */
fun View.startDragCompat(data: ImageDragData): Boolean {
    val shadowBuilder = View.DragShadowBuilder(this)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        startDragAndDrop(data.toClipData(), shadowBuilder, data, View.DRAG_FLAG_OPAQUE)
    } else {
        @Suppress("DEPRECATION")
        startDrag(data.toClipData(), shadowBuilder, data, 0)
    }
}
