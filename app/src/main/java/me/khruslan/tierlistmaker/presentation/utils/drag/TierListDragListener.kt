package me.khruslan.tierlistmaker.presentation.utils.drag

import android.content.ClipData
import android.graphics.PointF
import android.view.DragEvent
import android.view.View
import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.presentation.models.drag.DragLocation
import me.khruslan.tierlistmaker.presentation.models.drag.DragState
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import timber.log.Timber

/**
 * A wrapper on the [View.OnDragListener] specific to the tier list.
 *
 * Exposes drag events with the necessary [DragData].
 *
 * @constructor Default empty constructor.
 */
abstract class TierListDragListener : View.OnDragListener {

    /**
     * Current drag state.
     *
     * Changing the value of this field automatically triggers [onIsDraggingChanged] callback.
     */
    private var dragState = DragState.Idle
        set(value) {
            field = value
            onIsDraggingChanged(value == DragState.Dragging)
        }

    /**
     * Called when a new drag starts.
     *
     * @param dragData Image data that is dragged.
     */
    abstract fun onDragStarted(dragData: ImageDragData)

    /**
     * Called when the drag location changes.
     *
     * Note that target location could be null if the cursor leaves the drag area.
     *
     * @param dragLocation New drag location.
     */
    abstract fun onDragLocationChanged(dragLocation: DragLocation?)

    /**
     * Called when the image is dropped in the target.
     *
     * @param dragData Image data that is dropped.
     */
    abstract fun onDrop(dragData: ImageDragData)

    /**
     * Called when the drag ends without dropping.
     */
    abstract fun onDragEnded()

    /**
     * Called when dragging starts / ends.
     *
     * @param isDragging Whether the drag is active at the moment.
     */
    abstract fun onIsDraggingChanged(isDragging: Boolean)

    /**
     * Processes the drag event.
     *
     * On success may invoke the appropriate callback. On error logs it and resets the drag state.
     *
     * @param v The view that received the drag event.
     * @param event The event object for the drag event.
     * @return Whether the drag event was handled successfully.
     */
    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        try {
            when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> handleDragStartedAction(event)
                DragEvent.ACTION_DRAG_ENTERED -> handleDragEnteredAction(event, v)
                DragEvent.ACTION_DRAG_LOCATION -> handleDragLocationAction(event, v)
                DragEvent.ACTION_DRAG_EXITED -> handleDragExitedAction(event)
                DragEvent.ACTION_DROP -> handleDropAction(event)
                DragEvent.ACTION_DRAG_ENDED -> handleDragEndedAction(event)
                else -> handleNullOrUnknownAction(event)
            }
            return true
        } catch (e: TierListDragException) {
            Timber.e(e, "onDrag error")
            dragState = DragState.Idle
            return false
        }
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_STARTED] event.
     *
     * Updates drag state to [DragState.Dragging] and invokes [onDragStarted] callback if all
     * preconditions are met and drag state is [DragState.Idle].
     *
     * @param event The event with [DragEvent.ACTION_DRAG_STARTED] action.
     * @throws TierListDragException If event preconditions are not met.
     */
    private fun handleDragStartedAction(event: DragEvent) {
        checkEventPreconditions(event)

        if (dragState == DragState.Idle) {
            dragState = DragState.Dragging
            onDragStarted(event.localState as ImageDragData)
        }
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_ENTERED] event.
     *
     * Used only to check if all preconditions are met. If drag state is not [DragState.Dragging]
     * preconditions are not checked.
     *
     * @param event The event with [DragEvent.ACTION_DRAG_ENTERED] action.
     * @param view View that received the drag event.
     * @throws TierListDragException If preconditions are not met.
     */
    private fun handleDragEnteredAction(event: DragEvent, view: View?) {
        if (dragState != DragState.Dragging) return
        checkAllPreconditions(event, view)
        onDragLocationChanged(extractDragLocation(event, view))
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_LOCATION] event.
     *
     * Invokes [onDragLocationChanged] callback if all preconditions are met.
     *
     * @param event The event with [DragEvent.ACTION_DRAG_LOCATION] action.
     * @param view View that received the drag event.
     * @throws TierListDragException If preconditions are not met.
     */
    private fun handleDragLocationAction(event: DragEvent, view: View?) {
        if (dragState != DragState.Dragging) return
        checkAllPreconditions(event, view)
        onDragLocationChanged(extractDragLocation(event, view))
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_EXITED] event.
     *
     * Invokes [onDragLocationChanged] callback if drag state is [DragState.Dragging] and all
     * preconditions are met.
     *
     * @param event The event with [DragEvent.ACTION_DRAG_EXITED] action.
     * @throws TierListDragException If event preconditions are not met.
     */
    private fun handleDragExitedAction(event: DragEvent) {
        if (dragState != DragState.Dragging) return
        checkEventPreconditions(event)
        onDragLocationChanged(null)
    }

    /**
     * Processes [DragEvent.ACTION_DROP] event.
     *
     * Updates drag state to [DragState.Finishing] and invokes [onDrop] callback if all
     * preconditions are met.
     *
     * @param event The event with [DragEvent.ACTION_DROP] action.
     * @throws TierListDragException If event preconditions are not met.
     */
    private fun handleDropAction(event: DragEvent) {
        checkEventPreconditions(event)
        dragState = DragState.Finishing
        onDrop(createImageDragDataFromClip(event.clipData))
    }

    /**
     * Processes [DragEvent.ACTION_DRAG_ENDED] event.
     *
     * Updates drag state to [DragState.Idle] and invokes [onDragEnded] callback if all of the
     * conditions below are true:
     * - Drag state is not [DragState.Idle]
     * - All preconditions are met.
     * - Shadow was not dropped.
     *
     * @param event The event with [DragEvent.ACTION_DRAG_ENDED] action.
     * @throws TierListDragException If local state preconditions are not met.
     */
    private fun handleDragEndedAction(event: DragEvent) {
        if (dragState == DragState.Idle) return
        checkLocalStatePreconditions(event)
        dragState = DragState.Idle
        if (!event.result) onDragEnded()
    }

    /**
     * Processes the drag event when it's either null or unknown.
     *
     * Used only to get correct error message for the exception.
     *
     * @param event Unknown drag event or null.
     * @throws TierListDragException Always throws an error with the appropriate cause message.
     */
    private fun handleNullOrUnknownAction(event: DragEvent?) {
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
     * @param event Drag event to validate.
     * @throws TierListDragListener If local state is not [ImageDragData].
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
     * Checks if clip description of the event has correct label and local state preconditions are
     * met.
     *
     * @param event Drag event to validate.
     * @throws TierListDragException If clip description label is not [ImageDragData.LABEL] or local
     * state preconditions are not met.
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
     * Checks if all preconditions are met.
     *
     * Includes the following checks:
     * - Event preconditions are met.
     * - View must not be null.
     * - View must have tag of type [DragData].
     *
     * @param event Drag event to validate.
     * @param view View that received the drag event.
     * @throws TierListDragException If at least one of the preconditions wasn't met.
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
     * Creates image drag data from clip data.
     *
     * @param clipData Clip data from the drag event.
     * @return Crated image drag data.
     * @throws TierListDragException If unable to create.
     */
    private fun createImageDragDataFromClip(clipData: ClipData): ImageDragData {
        return try {
            ImageDragData.fromClipData(clipData)
        } catch (e: Exception) {
            throw TierListDragException("Unable to create ImageDragData from ClipData: ${e.message}")
        }
    }

    /**
     * Extracts drag location from drag event and view.
     *
     * @param event The event with [DragEvent.ACTION_DRAG_LOCATION] action.
     * @param view View that received the drag event.
     * @return Extracted location or null if drag target is unavailable.
     */
    private fun extractDragLocation(event: DragEvent, view: View?): DragLocation? {
        val dragTarget = view?.tag as DragData?
        return dragTarget?.let { target ->
            val positionInTarget = PointF(event.x, event.y)
            DragLocation(target, positionInTarget)
        }
    }

    /**
     * Readable name of the drag event action.
     *
     * Returns "UNKNOWN" if drag action has unexpected value.
     *
     * @receiver The event object for the drag event.
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
     * Exception for the errors that could happen inside the [TierListDragListener].
     *
     * @param message Error message of the exception.
     * @constructor Creates a new exception with error message.
     */
    private class TierListDragException(message: String) : Exception(message)
}