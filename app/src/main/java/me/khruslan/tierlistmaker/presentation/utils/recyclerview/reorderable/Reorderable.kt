package me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Ability of [RecyclerView.Adapter] that allows to move and/or swipe its items.
 *
 * In order to enable drag and drop functionality in your [RecyclerView] follow the steps below:
 * - [RecyclerView.Adapter] must implement [Reorderable].
 * - [ItemTouchHelper] with [ReorderableCallback] must be attached to [RecyclerView].
 */
interface Reorderable {

    /**
     * Notifies that item was moved.
     *
     * To apply changes, swap items and call [RecyclerView.Adapter.notifyItemMoved].
     *
     * @param fromPosition Initial position of the item.
     * @param toPosition Target position of the item.
     */
    fun onItemMove(fromPosition: Int, toPosition: Int)

    /**
     * Notifies that item was swiped.
     *
     * To apply changes, remove the item and call [RecyclerView.Adapter.notifyItemRemoved].
     *
     * @param position Position of the item.
     */
    fun onItemSwiped(position: Int)
}