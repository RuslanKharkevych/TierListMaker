package me.khruslan.tierlistmaker.utils.extensions

import android.os.Build
import android.view.View
import me.khruslan.tierlistmaker.data.drag.ImageDragData

fun View.hide() {
    visibility = View.GONE
}

fun View.startDragCompat(data: ImageDragData): Boolean {
    val shadowBuilder = View.DragShadowBuilder(this)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        startDragAndDrop(data.toClipData(), shadowBuilder, data, View.DRAG_FLAG_OPAQUE)
    } else {
        @Suppress("DEPRECATION")
        startDrag(data.toClipData(), shadowBuilder, data, 0)
    }
}