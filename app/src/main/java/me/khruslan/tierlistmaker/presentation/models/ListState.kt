package me.khruslan.tierlistmaker.presentation.models

/**
 * UI state of the list with asynchronously loaded data.
 *
 * Represents the status of data loading at a given point in time.
 *
 * @constructor Default empty constructor.
 */
enum class ListState {

    /**
     * Loading state.
     */
    Loading,

    /**
     * State of the empty list.
     */
    Empty,

    /**
     * State of the list that is not empty.
     */
    Filled,

    /**
     * State of the list that failed to load.
     */
    Failed
}