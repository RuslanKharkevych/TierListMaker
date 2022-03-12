package me.khruslan.tierlistmaker.utils.drag

import android.content.ClipData
import android.view.DragEvent
import android.view.View
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import timber.log.Timber

abstract class TierListDragListener : View.OnDragListener {
    private var isDragging = false

    abstract fun onDragStarted(dragData: ImageDragData)
    abstract fun onDragTargetChanged(targetData: DragData?)
    abstract fun onDrop(dragData: ImageDragData)
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

    private fun handleDragStartedAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)

        if (!isDragging) {
            isDragging = true
            onDragStarted(event.localState as ImageDragData)
        }

        return true
    }

    private fun handleDragEnteredAction(event: DragEvent, view: View?): Boolean {
        checkAllPreconditions(event, view)
        return true
    }

    private fun handleDragLocationAction(event: DragEvent, view: View?): Boolean {
        checkAllPreconditions(event, view)
        onDragTargetChanged(view?.tag as DragData?)
        return true
    }

    private fun handleDragExitedAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)
        onDragTargetChanged(null)
        return true
    }

    private fun handleDropAction(event: DragEvent): Boolean {
        checkEventPreconditions(event)
        onDrop(createImageDragDataFromClip(event.clipData))
        return true
    }

    private fun handleDragEndedAction(event: DragEvent): Boolean {
        checkLocalStatePreconditions(event)
        if (!isDragging) return false
        if (!event.result) onDragEnded()
        return false
    }

    private fun handleNullOrUnknownAction(event: DragEvent?): Boolean {
        throw TierListDragException(
            if (event == null) {
                "event is null"
            } else {
                "event has unknown action (${event.action})"
            }
        )
    }

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

    private fun createImageDragDataFromClip(clipData: ClipData) = try {
        ImageDragData.fromClipData(clipData)
    } catch (e: Exception) {
        throw TierListDragException("Unable to create ImageDragData from ClipData: ${e.message}")
    }

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
}