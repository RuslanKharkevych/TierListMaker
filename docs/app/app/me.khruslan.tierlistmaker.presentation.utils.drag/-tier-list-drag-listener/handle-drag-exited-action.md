//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDragExitedAction](handle-drag-exited-action.md)

# handleDragExitedAction

private fun [handleDragExitedAction](handle-drag-exited-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))

Processes [DragEvent.ACTION_DRAG_EXITED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_exited) event.

Invokes [onDragLocationChanged](on-drag-location-changed.md) callback if drag state is [DragState.Dragging](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-dragging/index.md) and all preconditions are met.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_EXITED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_exited) action. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If event preconditions are not met. |
