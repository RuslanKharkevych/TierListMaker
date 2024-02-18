package me.khruslan.tierlistmaker.presentation.models.drag

/**
 * UI state of dragging.
 *
 * Indicates whether the dragging is happening at a given point in time.
 *
 * @constructor Default empty constructor.
 */
enum class DragState {

    /**
     * Dragging is finished or hasn't been started yet. New drag can be started.
     */
    Idle,

    /**
     * Dragging is in progress.
     */
    Dragging,

    /**
     * Dragging is about to finish. New drag can't be started yet.
     */
    Finishing
}