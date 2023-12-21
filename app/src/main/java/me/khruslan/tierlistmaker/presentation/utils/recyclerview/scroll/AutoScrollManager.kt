package me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.presentation.models.drag.DragLocation
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.presentation.models.drag.TargetLocation
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.presentation.models.scroll.AutoScrollDirection
import me.khruslan.tierlistmaker.presentation.models.scroll.AutoScrollState
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.util.dpToPx

/**
 * Manager that performs automatic scrolling of the attached [RecyclerView] when dragged image
 * is moved to the vertical edge of the visible tier list area.
 *
 * @property recyclerView attached recycler view. Must have [RecyclerView.Adapter] and
 * [LinearLayoutManager] already set before creating [AutoScrollManager] instance.
 */
class AutoScrollManager(private val recyclerView: RecyclerView) {

    /**
     * Companion object of [AutoScrollManager] used for storing constants.
     */
    private companion object {
        private const val SCROLL_OFFSET_DP = 5f
    }

    /**
     * Unsigned offset in pixels of a single scrolling step.
     */
    private val scrollOffsetPx = recyclerView.context.dpToPx(SCROLL_OFFSET_DP).toInt()

    /**
     * Current state of automatic scrolling.
     */
    private var scrollState: AutoScrollState = AutoScrollState.Idle

    /**
     * Helper for computing scroll state.
     */
    private val scrollHelper = AutoScrollHelper(
        context = recyclerView.context,
        layoutManager = recyclerView.layoutManager as LinearLayoutManager
    )

    /**
     * [RecyclerView.OnScrollListener] that is attached to [recyclerView]. Detects when scrolling
     * ends and keeps continuously scrolling in the same direction until [scrollState] changes.
     */
    private val scrollListener
        get() = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                performScrollIfNeeded()
            }
        }

    init {
        recyclerView.addOnScrollListener(scrollListener)
    }

    /**
     * Updates current drag location, recomputes scroll state and performs automatic scrolling if
     * needed.
     *
     * @param dragLocation new drag location or null cursor leaved the drag area.
     */
    fun updateDragLocation(dragLocation: DragLocation?) {
        scrollState = computeScrollState(dragLocation)
        performScrollIfNeeded()
    }

    /**
     * Stops automatic scrolling.
     */
    fun stopScrolling() {
        scrollState = AutoScrollState.Idle
    }

    /**
     * Computes [AutoScrollState] based on drag location. If [DragLocation.target] is neither
     * [TierDragData] nor [ImageDragData] - returns [AutoScrollState.Idle]. Same if target's
     * tier position is [BACKLOG_TIER_POSITION]. Otherwise delegates to [scrollState] to
     * determine whether auto scrolling is needed .
     *
     * @param dragLocation
     * @return
     */
    private fun computeScrollState(dragLocation: DragLocation?): AutoScrollState {
        val adapterPosition = when (val target = dragLocation?.target) {
            is TierDragData -> target.tierPosition
            is ImageDragData -> target.tierPosition
            else -> return AutoScrollState.Idle
        }
        val verticalOffset = dragLocation.positionInTarget.y.toInt()
        val targetLocation = TargetLocation(adapterPosition, verticalOffset)

        return when {
            adapterPosition == BACKLOG_TIER_POSITION -> AutoScrollState.Idle
            scrollHelper.shouldScrollUp(targetLocation) -> AutoScrollState.scrollingUp()
            scrollHelper.shouldScrollDown(targetLocation) -> AutoScrollState.scrollingDown()
            else -> AutoScrollState.Idle
        }
    }

    /**
     * If [scrollState] is [AutoScrollState.Scrolling] performs one-time smooth scroll to the
     * appropriate direction. Does nothing if [scrollState] is [AutoScrollState.Idle].
     */
    private fun performScrollIfNeeded() {
        scrollState.let { state ->
            if (state is AutoScrollState.Scrolling) {
                val scrollOffset = getScrollOffset(state.direction)
                if (recyclerView.canScrollVertically(scrollOffset)) {
                    recyclerView.smoothScrollBy(0, scrollOffset)
                }
            }
        }
    }

    /**
     * Returns scroll offset for the given direction.
     *
     * @param direction scroll direction.
     * @return scroll offset in pixels.
     */
    private fun getScrollOffset(direction: AutoScrollDirection): Int {
        return when (direction) {
            AutoScrollDirection.Up -> -scrollOffsetPx
            AutoScrollDirection.Down -> scrollOffsetPx
        }
    }
}