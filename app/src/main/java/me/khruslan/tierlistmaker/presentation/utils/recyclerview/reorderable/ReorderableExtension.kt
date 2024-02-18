package me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Attaches [ItemTouchHelper] with [ReorderableCallback] to the [RecyclerView].
 *
 * This function requires that [RecyclerView] has set [RecyclerView.Adapter] that implements
 * [Reorderable] interface. Enables both changing the order of items and swipe-to-dismiss
 * functionality.
 *
 * @receiver Recycler view for which reordering will be enabled.
 */
fun RecyclerView.enableReordering() {
    val callback = ReorderableCallback(adapter as Reorderable)
    ItemTouchHelper(callback).attachToRecyclerView(this)
}