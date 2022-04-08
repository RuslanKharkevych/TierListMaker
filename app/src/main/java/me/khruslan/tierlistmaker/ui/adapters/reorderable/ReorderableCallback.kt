package me.khruslan.tierlistmaker.ui.adapters.reorderable

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * [ItemTouchHelper.Callback] implementation that delegates events to the provided [Reorderable].
 * Up and down drag flags and right swipe flag are enabled.
 * Therefore, it should be used in vertically scrolled lists only.
 *
 * In order to enable drag and drop functionality in your [RecyclerView] follow the steps below:
 * - [RecyclerView.Adapter] must implement [Reorderable].
 * - [ItemTouchHelper] with [ReorderableCallback] must be attached to [RecyclerView].
 *
 * @property reorderable reorderable that will receive drag and drop events.
 */
class ReorderableCallback(private val reorderable: Reorderable) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        reorderable.onItemMove(
            fromPosition = viewHolder.adapterPosition,
            toPosition = target.adapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        reorderable.onItemSwiped(viewHolder.adapterPosition)
    }
}