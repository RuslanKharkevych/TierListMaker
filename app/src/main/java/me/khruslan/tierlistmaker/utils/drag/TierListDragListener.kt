package me.khruslan.tierlistmaker.utils.drag

import android.content.ClipData
import android.view.DragEvent
import android.view.View
import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import timber.log.Timber

/**
 * A wrapper on the [View.OnDragListener] specific to the tier list.
 * Exposes drag events with the necessary [DragData].
 */
abstract class TierListDragListener : View.OnDragListener {

    /**
     * Whether the drag is active at the moment.
     */
    private var isDragging = false

    /**
     * Called when the drag is started.
     *
     * @param dragData image data that is dragged.
     */
    abstract fun onDragStarted(dragData: ImageDragData)

    /**
     * Called when the drag target has changed.
     *
     * Note that target data could be **null** if the cursor leaves the drag area.
     *
     * @param targetData new drag target data.
     */
    abstract fun onDragTargetChanged(targetData: DragData?)

    /**
     * Called when the image is dropped in the target.
     *
     * @param dragData image data that is dropped.
     */
    abstract fun onDrop(dragData: ImageDragData)

    /**
     * Called when the drag ends without dropping.
     */
    abstract fun onDragEnded()

    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        try {
            val result = when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> handleDragStartedAction(event)
                DragEvent.ACTION_DRAG_ENTERED -> handleDragEnteredAction(event, view)
                DragEvent.ACTION_DRAG_LOCATION -> handleDragLocationAction(event, view)
                DragEvent.ACTION_DRAG_EXITED -> handleDragExitedAction(event)
                DragEvent.ACTION_DROP -> handleDropAction(event)
                DragEvent.ACTION_DRAG_ENDED -> handleDragEndedAction(event)
                else -> handleNullOrUnknownAction(event)
            }

            if (!result) isDragging = false
            return result
        } catch (e: TierListDragException) {
            Timber.e(e, "onDrag error")
            isDragging = false
            return false
        }
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_STARTED] event.
     *
     * Invokes [onDragStarted] function if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DRAG_STARTED]).
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDragStartedAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)

        if (!isDragging) {
            isDragging = true
            onDragStarted(event.localState as ImageDragData)
        }

        return true
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_ENTERED] event.
     *
     * Used only to check if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DRAG_ENTERED]).
     * @param view view that received the drag event.
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDragEnteredAction(event: DragEvent, view: View?): Boolean {
        checkAllPreconditions(event, view)
        return true
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_LOCATION] event.
     *
     * Invokes [onDragTargetChanged] function if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DRAG_LOCATION]).
     * @param view view that received the drag event.
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDragLocationAction(event: DragEvent, view: View?): Boolean {
        checkAllPreconditions(event, view)
        onDragTargetChanged(view?.tag as DragData?)
        return true
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_EXITED] event.
     *
     * Invokes [onDragTargetChanged] function if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DRAG_EXITED]).
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDragExitedAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)
        onDragTargetChanged(null)
        return true
    }

    /**
     * Processes [DragEvent.ACTION_DROP] event.
     *
     * Invokes [onDrop] function if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DROP]).
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDropAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)
        onDrop(createImageDragDataFromClip(event.clipData))
        return true
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_ENDED] event.
     *
     * Invokes [onDragEnded] function if all preconditions are met.
     *
     * @param event the event to handle (should always be [DragEvent.ACTION_DRAG_ENDED]).
     * @return Whether the event was handled successfully (is always true).
     */
    private fun handleDragEndedAction(event: DragEvent): Boolean {
        checkLocalStatePreconditions(event)
        if (!isDragging) return false
        if (!event.result) onDragEnded()
        return false
    }

    /**
     * Processes the drag event when it's either null or unknown.
     *
     * Used only to get correct error message for the exception.
     *
     * @param event unknown drag event or null.
     * @return Whether the event was handled successfully (never returns anything).
     * @throws TierListDragException always throws an error with the appropriate cause message.
     */
    private fun handleNullOrUnknownAction(event: DragEvent?): Boolean {
        throw TierListDragException(
            if (event == null) {
                "event is null"
            } else {
                "event has unknown action (${event.action})"
            }
        )
    }

    /**
     * Checks if event has correct local state.
     *
     * @param event drag event to validate.
     * @throws TierListDragListener if local state is not [ImageDragData].
     */
    private fun checkLocalStatePreconditions(event: DragEvent) {
        if (event.localState !is ImageDragData) {
            throw TierListDragException(
                String.format(
                    "event (%s) has incompatible localState type (%s)",
                    event.actionName,
                    event.localState
                )
            )
        }
    }

    /**
     * Checks if clip description of the event has correct label
     * and local state preconditions are met.
     *
     * @param event drag event to validate.
     * @throws TierListDragException if clip description label is not [ImageDragData.LABEL].
     */
    private fun checkEventPreconditions(event: DragEvent) {
        if (event.clipDescription.label != ImageDragData.LABEL) {
            throw TierListDragException(
                String.format(
                    "event (%s) has incompatible clipDescription (%s)",
                    event.actionName,
                    event.clipDescription.label
                )
            )
        } else {
            checkLocalStatePreconditions(event)
        }
    }

    /**
     * Checks if all of the preconditions below are met:
     * - event preconditions
     * - view must not be null
     * - view must have tag of type [DragData]
     *
     * @param event drag event to validate.
     * @param view view that received the drag event.
     * @throws TierListDragException if at least one of the preconditions wasn't met.
     */
    private fun checkAllPreconditions(event: DragEvent, view: View?) {
        checkEventPreconditions(event)

        if (view == null) {
            throw TierListDragException("view is null for event (${event.actionName})")
        }

        if (view.tag !is DragData) {
            throw TierListDragException(
                String.format(
                    "event (%s) has incompatible view tag (%s)",
                    event.actionName,
                    view.tag
                )
            )
        }
    }

    /**
     * Creates [ImageDragData] from [ClipData].
     *
     * @param clipData [ClipData] from the drag event.
     * @return crated [ImageDragData].
     * @throws TierListDragException if unable to create.
     */
    private fun createImageDragDataFromClip(clipData: ClipData): ImageDragData {
        return try {
            ImageDragData.fromClipData(clipData)
        } catch (e: Exception) {
            throw TierListDragException("Unable to create ImageDragData from ClipData: ${e.message}")
        }
    }

    /**
     * Readable name of the drag event action.
     */
    private val DragEvent.actionName
        get() = when (action) {
            DragEvent.ACTION_DRAG_STARTED -> "ACTION_DRAG_STARTED"
            DragEvent.ACTION_DRAG_ENTERED -> "ACTION_DRAG_ENTERED"
            DragEvent.ACTION_DRAG_LOCATION -> "ACTION_DRAG_LOCATION"
            DragEvent.ACTION_DROP -> "ACTION_DROP"
            DragEvent.ACTION_DRAG_EXITED -> "ACTION_DRAG_EXITED"
            DragEvent.ACTION_DRAG_ENDED -> "ACTION_DRAG_ENDED"
            else -> "UNKNOWN"
        }

    /**
     * [Exception] implementation for the errors that could happen inside the [TierListDragListener].
     *
     * @param message error message of the exception.
     */
    private class TierListDragException(message: String) : Exception(message)
}