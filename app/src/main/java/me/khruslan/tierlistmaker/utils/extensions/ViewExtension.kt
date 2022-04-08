package me.khruslan.tierlistmaker.utils.extensions

import android.os.Build
import android.view.View
import me.khruslan.tierlistmaker.data.drag.ImageDragData

/**
 * Sets visibility of the [View] to [View.INVISIBLE].
 * @receiver Any [View].
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * A helper function to starts drag that supports all version.
 *
 * Starting from [Build.VERSION_CODES.N] the shadow will be fully opaque.
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