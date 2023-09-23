package me.khruslan.tierlistmaker.utils.scroll

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import me.khruslan.tierlistmaker.data.models.drag.TargetLocation
import me.khruslan.tierlistmaker.utils.dpToPx
import timber.log.Timber
import kotlin.math.min

/**
 * Helper that checks whether automatic scrolling should be performed depending on the current
 * target location.
 *
 * @property layoutManager layout manager of the recycler view with auto scrolling functionality.
 * @param context context for resolving resources.
 */
class AutoScrollChecker(context: Context, private val layoutManager: LinearLayoutManager) {

    /**
     * Companion object of [AutoScrollChecker] used for storing constants.
     */
    private companion object {
        private const val AUTOSCROLL_REGION_HEIGHT_DP = 30f
    }

    /**
     * The region where the cursor must be located for auto scrolling to occur.
     */
    private val autoScrollRegionHeightPx = context.dpToPx(AUTOSCROLL_REGION_HEIGHT_DP)

    /**
     * Checks if view should scroll up (when the target is located in the top autoscroll region).
     *
     * @param targetLocation current location of the target.
     * @return whether auto scrolling up should be performed.
     */
    fun shouldScrollUp(targetLocation: TargetLocation): Boolean {
        return shouldScroll(Up(), targetLocation)
    }

    /**
     * Checks if view should scroll down (when the target is located in the bottom autoscroll
     * region).
     *
     * @param targetLocation current location of the target.
     * @return whether auto scrolling down should be performed.
     */
    fun shouldScrollDown(targetLocation: TargetLocation): Boolean {
        return shouldScroll(Down(), targetLocation)
    }

    /**
     * Checks if view should scroll to the given direction. Loops through visible views to
     * calculate distance to target (from top or bottom depending on the strategy). Compares
     * resulting distance with [autoScrollRegionHeightPx] to determine if target is within
     * autoscroll region.
     *
     * @param strategy direction strategy (up or down).
     * @param location current location of the target.
     * @return whether auto scrolling should be performed. Returns **false** if calculation was
     * unsuccessful due to unexpected error.
     */
    private fun shouldScroll(strategy: DirectionStrategy, location: TargetLocation): Boolean {
        var distanceToTarget = 0

        for (position in strategy.getItemPositions(location.targetPosition)) {
            val view = layoutManager.findViewByPosition(position) ?: run {
                logError(position, strategy, location)
                return false
            }

            distanceToTarget += if (position == location.targetPosition) {
                strategy.calculateDistanceToTarget(view, location.targetOffset)
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
     * @receiver the view for which visible height should be calculated.
     * @return visible height of the view in pixels.
     */
    private fun View.getVisibleHeight(): Int {
        val visibleRect = Rect()
        getGlobalVisibleRect(visibleRect)
        return visibleRect.height()
    }

    /**
     * Logs error that can happen during [shouldScroll] calculations. Includes all passed
     * parameters in error message.
     *
     * @param position item position in layout manager.
     * @param strategy direction strategy.
     * @param location target location.
     */
    private fun logError(position: Int, strategy: DirectionStrategy, location: TargetLocation) {
        val additionalInfo = "strategy: $strategy: location: $location"
        val message = "Unable to find view by position $position ($additionalInfo)"
        val exception = AutoScrollCheckerException(message)
        Timber.e(exception, "Unable to check if should scroll")
    }

    /**
     * Exception for errors that can happen during autoscroll calculations.
     *
     * @param message error message.
     */
    private class AutoScrollCheckerException(message: String) : Exception(message)

    /**
     * Strategy used by [shouldScroll] algorithm. Can be either [Up] or [Down] depending on the
     * scroll direction. Provides item positions that must be looped through in order to calculate
     * distance to target. Describes how to calculate distance to target withing the item view.
     */
    private interface DirectionStrategy {

        /**
         * Provides progression of the item positions that must be looped through in order to
         * calculate distance to target.
         *
         * @param targetPosition position of the item view that contains target.
         * @return the progression of item positions that must be looped through.
         */
        fun getItemPositions(targetPosition: Int): IntProgression

        /**
         * Calculates distance to target withing the item view.
         *
         * @param view item view that contains target.
         * @param targetOffset vertical offset of target withing the view.
         * @return distance to target in pixels.
         */
        fun calculateDistanceToTarget(view: View, targetOffset: Int): Int
    }

    /**
     * Implementation of [DirectionStrategy] for checking if view should scroll up.
     */
    private inner class Up : DirectionStrategy {

        override fun getItemPositions(targetPosition: Int): IntProgression {
            return layoutManager.findFirstVisibleItemPosition()..targetPosition
        }

        override fun calculateDistanceToTarget(view: View, targetOffset: Int): Int {
            return min(view.top, 0) + targetOffset
        }
    }

    /**
     * Implementation of [DirectionStrategy] for checking if view should scroll down.
     */
    private inner class Down : DirectionStrategy {

        override fun getItemPositions(targetPosition: Int): IntProgression {
            return layoutManager.findLastVisibleItemPosition() downTo targetPosition
        }

        override fun calculateDistanceToTarget(view: View, targetOffset: Int): Int {
            return view.getVisibleHeight() - targetOffset
        }
    }
}