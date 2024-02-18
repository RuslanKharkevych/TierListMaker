package me.khruslan.tierlistmaker.presentation.models.scroll

/**
 * UI state of automatic scrolling.
 *
 * Indicates whether the automatic scrolling is happening at a given point in time..
 *
 * @constructor Default empty constructor.
 */
sealed class AutoScrollState {

    /**
     * Convenience functions for creating [AutoScrollState.Scrolling].
     */
    companion object Helpers {

        /**
         * Creates [AutoScrollState.Scrolling] with [AutoScrollDirection.Up].
         *
         * @return Created state.
         */
        fun scrollingUp(): AutoScrollState {
            return Scrolling(AutoScrollDirection.Up)
        }

        /**
         * Creates [AutoScrollState.Scrolling] with [AutoScrollDirection.Down].
         *
         * @return Created state.
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
     * @property direction Autoscroll direction.
     * @constructor Creates a scrolling state from the direction.
     */
    data class Scrolling(val direction: AutoScrollDirection) : AutoScrollState()
}