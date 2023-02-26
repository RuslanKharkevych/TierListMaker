package me.khruslan.tierlistmaker.ui.models

/**
 * UI State that represents a process of loading. Could be either [Determinate] or [Indeterminate].
 */
sealed class LoadingProgress {

    /**
     * UI state for the determinate progress of loading items.
     *
     * @property currentItem number of the item that is loading now.
     * @property totalItems total number of items that should be loaded.
     */
    data class Determinate(val currentItem: Int, val totalItems: Int) : LoadingProgress() {

        /**
         * Loading progress in percentage (from 0 to 100).
         */
        val percent = ((currentItem.toFloat() / totalItems) * 100).toInt()
    }

    /**
     * UI state for the indeterminate progress of loading.
     */
    object Indeterminate : LoadingProgress()
}