package me.khruslan.tierlistmaker.data.state

/**
 * UI state for the list with dynamically loaded data.
 *
 * @see [ListState.Loading]
 * @see [ListState.Empty]
 * @see [ListState.Filled]
 * @see [ListState.Failed]
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