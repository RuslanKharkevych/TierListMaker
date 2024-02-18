package me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import me.khruslan.tierlistmaker.presentation.models.drag.TargetLocation
import me.khruslan.tierlistmaker.util.dpToPx
import timber.log.Timber
import kotlin.math.min

/**
 * Helper that checks whether automatic scrolling should be performed depending on the current
 * target location.
 *
 * @property layoutManager Layout manager of the recycler view with auto scrolling functionality.
 * @param context Context for resolving resources.
 * @constructor Creates a new auto scroll helper for the supplied layout manager.
 */
class AutoScrollHelper(context: Context, private val layoutManager: LinearLayoutManager) {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * The height of the auto scroll region in DP.
         *
         * Auto-scroll region is an area where the cursor must be located for auto scrolling to
         * occur.
         */
        private const val AUTOSCROLL_REGION_HEIGHT_DP = 30f
    }

    /**
     * The height of the auto scroll region in pixels.
     *
     * Auto-scroll region is an area where the cursor must be located for auto scrolling to occur.
     */
    private val autoScrollRegionHeightPx = context.dpToPx(AUTOSCROLL_REGION_HEIGHT_DP)

    /**
     * Checks if view should scroll up.
     *
     * Scrolling up is performed when the target is located in the top autoscroll region.
     *
     * @param targetLocation Current location of the target.
     * @return Whether auto scrolling up should be performed.
     */
    fun shouldScrollUp(targetLocation: TargetLocation): Boolean {
        return shouldScroll(Up(), targetLocation)
    }

    /**
     * Checks if view should scroll down.
     *
     * Scrolling down is performed when the target is located in the bottom autoscroll region.
     *
     * @param targetLocation Current location of the target.
     * @return Whether auto scrolling down should be performed.
     */
    fun shouldScrollDown(targetLocation: TargetLocation): Boolean {
        return shouldScroll(Down(), targetLocation)
    }

    /**
     * Checks if view should scroll to the given direction.
     *
     * Loops through visible views to calculate distance to target (from top or bottom depending on
     * the strategy). Compares resulting distance with [autoScrollRegionHeightPx] to determine if
     * target is within autoscroll region.
     *
     * @param strategy Direction strategy (up or down).
     * @param location Current location of the target.
     * @return Whether auto scrolling should be performed. Returns false if calculation was
     * unsuccessful due to unexpected error.
     */
    private fun shouldScroll(strategy: DirectionStrategy, location: TargetLocation): Boolean {
        var distanceToTarget = 0

        for (position in strategy.getItemPositions(location.adapterPosition)) {
            val view = layoutManager.findViewByPosition(position) ?: run {
                logError(position, strategy, location)
                return false
            }

            distanceToTarget += if (position == location.adapterPosition) {
                strategy.calculateDistanceToTarget(view, location.verticalOffset)
            } else {
                view.getVisibleHeight()
            }

            if (distanceToTarget > autoScrollRegionHeightPx) return false
        }

        return true
    }

    /**
     * Calculates height of the global visible rectangle of the given view.
     *
     * @receiver The view for which visible height should be calculated.
     * @return Visible height of the view in pixels.
     */
    private fun View.getVisibleHeight(): Int {
        val visibleRect = Rect()
        getGlobalVisibleRect(visibleRect)
        return visibleRect.height()
    }

    /**
     * Logs error that can happen during [shouldScroll] calculations.
     *
     * Includes all passed parameters in error message.
     *
     * @param position Item position in layout manager.
     * @param strategy Direction strategy.
     * @param location Target location.
     */
    private fun logError(position: Int, strategy: DirectionStrategy, location: TargetLocation) {
        val additionalInfo = "strategy: $strategy: location: $location"
        val message = "Unable to find view by position $position ($additionalInfo)"
        val exception = AutoScrollHelperException(message)
        Timber.e(exception, "Unable to check if should scroll")
    }

    /**
     * Exception for errors that can happen during autoscroll calculations.
     *
     * @param message Error message.
     * @constructor Creates exception with error message.
     */
    private class AutoScrollHelperException(message: String) : Exception(message)

    /**
     * Strategy used by [shouldScroll] algorithm.
     *
     * Can be either [Up] or [Down] depending on the scroll direction. Provides item positions that
     * must be looped through in order to calculate distance to target. Describes how to calculate
     * distance to target withing the item view.
     */
    private interface DirectionStrategy {

        /**
         * Provides progression of the item positions that must be looped through in order to
         * calculate distance to target.
         *
         * @param targetPosition Position of the item view that contains target.
         * @return The progression of item positions that must be looped through.
         */
        fun getItemPositions(targetPosition: Int): IntProgression

        /**
         * Calculates distance to target withing the item view.
         *
         * @param view Item view that contains target.
         * @param targetOffset Vertical offset of target withing the view.
         * @return Distance to target in pixels.
         */
        fun calculateDistanceToTarget(view: View, targetOffset: Int): Int
    }

    /**
     * Direction strategy for checking if view should scroll up.
     * @constructor Creates a new direction strategy.
     */
    private inner class Up : DirectionStrategy {

        /**
         * Provides progression from the first visible item position to the target position.
         *
         * @param targetPosition Position of the item view that contains target.
         * @return The progression of item positions that must be looped through.
         */
        override fun getItemPositions(targetPosition: Int): IntProgression {
            return layoutManager.findFirstVisibleItemPosition()..targetPosition
        }

        /**
         * Calculates distance to target withing the item view.
         *
         * @param view Item view that contains target.
         * @param targetOffset Vertical offset of target withing the view.
         * @return Distance to target in pixels.
         */
        override fun calculateDistanceToTarget(view: View, targetOffset: Int): Int {
            return min(view.top, 0) + targetOffset
        }
    }

    /**
     * Direction strategy for checking if view should scroll down.
     * @constructor Creates a new direction strategy.
     */
    private inner class Down : DirectionStrategy {

        /**
         * Provides progression from the last visible item position down to the target position.
         *
         * @param targetPosition Position of the item view that contains target.
         * @return The progression of item positions that must be looped through.
         */
        override fun getItemPositions(targetPosition: Int): IntProgression {
            return layoutManager.findLastVisibleItemPosition() downTo targetPosition
        }

        /**
         * Calculates distance to target withing the item view.
         *
         * @param view Item view that contains target.
         * @param targetOffset Vertical offset of target withing the view.
         * @return Distance to target in pixels.
         */
        override fun calculateDistanceToTarget(view: View, targetOffset: Int): Int {
            return view.getVisibleHeight() - targetOffset
        }
    }
}