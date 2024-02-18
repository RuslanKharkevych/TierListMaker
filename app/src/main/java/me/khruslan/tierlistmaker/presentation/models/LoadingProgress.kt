package me.khruslan.tierlistmaker.presentation.models

/**
 * UI state that represents a process of loading.
 *
 * Could be either determinate or indeterminate.
 *
 * @constructor Default empty constructor.
 */
sealed class LoadingProgress {

    /**
     * UI state for the determinate progress of loading items.
     *
     * Note that this class is immutable.
     *
     * @property currentItem Index of the item that is loading now.
     * @property totalItems Total number of items that should be loaded.
     * @constructor Creates a new instance of the determinate loading progress class.
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
    data object Indeterminate : LoadingProgress()
}