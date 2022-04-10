package me.khruslan.tierlistmaker.data.state

/**
 * UI state for the determinate progress of loading items.
 *
 * @property currentItem number of the item that is loading now.
 * @property totalItems total number of items that should be loaded.
 */
data class LoadingProgress(val currentItem: Int, val totalItems: Int) {

    /**
     * Loading progress in percentage (from 0 to 100).
     */
    val percent = ((currentItem.toFloat() / totalItems) * 100).toInt()
}