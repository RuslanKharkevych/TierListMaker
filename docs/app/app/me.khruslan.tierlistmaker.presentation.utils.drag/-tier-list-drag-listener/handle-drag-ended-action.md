//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDragEndedAction](handle-drag-ended-action.md)

# handleDragEndedAction

private fun [handleDragEndedAction](handle-drag-ended-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))

Processes [DragEvent.ACTION_DRAG_ENDED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_ended) event.

Updates drag state to [DragState.Idle](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-idle/index.md) and invokes [onDragEnded](on-drag-ended.md) callback if all of the conditions below are true:

- Drag state is not [DragState.Idle](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-idle/index.md)
- All preconditions are met.
- Shadow was not dropped.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_ENDED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_ended) action. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If local state preconditions are not met. |
