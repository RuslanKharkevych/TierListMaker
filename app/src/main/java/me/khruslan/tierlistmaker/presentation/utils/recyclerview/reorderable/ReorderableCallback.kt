package me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Class that delegates [ItemTouchHelper.Callback] events to the provided [Reorderable].
 *
 * Up and down drag flags and right swipe flag are enabled. Therefore, it should be used in
 * vertically scrolled lists only. In order to enable drag and drop functionality in your
 * [RecyclerView] follow the steps below:
 * - [RecyclerView.Adapter] must implement [Reorderable].
 * - [ItemTouchHelper] with [ReorderableCallback] must be attached to [RecyclerView].
 *
 * @property reorderable Reorderable that will receive drag and drop events.
 * @constructor Creates a new reorderable callback
 */
class ReorderableCallback(private val reorderable: Reorderable) : ItemTouchHelper.Callback() {

    /**
     * Returns a composite flag which defines the enabled move directions in each state (idle,
     * swiping, dragging).
     *
     * Allows the items to be drag & dropped vertically and swiped left to be dismissed,
     *
     * @param recyclerView The recycler view to which [ItemTouchHelper] is attached.
     * @param viewHolder The view holder for which the movement information is necessary.
     * @return Flags specifying which movements are allowed on this view holder.
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    /**
     * Called when [ItemTouchHelper] wants to move the dragged item from its old position to the new
     * position.
     *
     * Invokes [Reorderable.onItemMove] callback.
     *
     * @param recyclerView The recycler view to which [ItemTouchHelper] is attached to.
     * @param viewHolder The view holder which is being dragged by the user.
     * @param target The view holder over which the currently active item is being dragged.
     * @return Always returns true, assuming that the [viewHolder] has been moved to the adapter
     * position of [target].
     */
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

    /**
     * Called when a view holder is swiped by the user.
     *
     * Invokes [Reorderable.onItemSwiped] callback.
     *
     * @param viewHolder The view holder which has been swiped by the user.
     * @param direction  The direction to which the view holder is swiped.
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        reorderable.onItemSwiped(viewHolder.adapterPosition)
    }
}