package me.khruslan.tierlistmaker.presentation.models.scroll

/**
 * State of automatic scrolling.
 */
sealed class AutoScrollState {

    /**
     * Companion object of [AutoScrollState] that exposes functions for simplified creation of
     * [AutoScrollState.Scrolling] state.
     */
    companion object {

        /**
         * Creates [AutoScrollState.Scrolling] with [AutoScrollDirection.Up].
         *
         * @return created state.
         */
        fun scrollingUp(): AutoScrollState {
            return Scrolling(AutoScrollDirection.Up)
        }

        /**
         * Creates [AutoScrollState.Scrolling] with [AutoScrollDirection.Down].
         *
         * @return created state.
         */
        fun scrollingDown(): AutoScrollState {
            return Scrolling(AutoScrollDirection.Down)
        }
    }

    /**
     * Inactive state. Scrolling is not happening at the moment.
     */
    data object Idle : AutoScrollState()

    /**
     * Active state. View is being automatically scrolled.
     *
     * @property direction autoscroll direction.
     */
    data class Scrolling(val direction: AutoScrollDirection) : AutoScrollState()
}