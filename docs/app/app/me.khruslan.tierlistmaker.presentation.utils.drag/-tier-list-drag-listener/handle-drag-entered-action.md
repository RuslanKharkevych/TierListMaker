//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDragEnteredAction](handle-drag-entered-action.md)

# handleDragEnteredAction

private fun [handleDragEnteredAction](handle-drag-entered-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)

Processes [DragEvent.ACTION_DRAG_ENTERED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_entered) event.

Used only to check if all preconditions are met. If drag state is not [DragState.Dragging](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-dragging/index.md) preconditions are not checked.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_ENTERED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_entered) action. |
| view | View that received the drag event. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If preconditions are not met. |
