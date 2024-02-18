//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDragStartedAction](handle-drag-started-action.md)

# handleDragStartedAction

private fun [handleDragStartedAction](handle-drag-started-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))

Processes [DragEvent.ACTION_DRAG_STARTED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_started) event.

Updates drag state to [DragState.Dragging](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-dragging/index.md) and invokes [onDragStarted](on-drag-started.md) callback if all preconditions are met and drag state is [DragState.Idle](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-idle/index.md).

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_STARTED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_started) action. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If event preconditions are not met. |
