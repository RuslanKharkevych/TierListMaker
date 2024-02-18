//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDropAction](handle-drop-action.md)

# handleDropAction

private fun [handleDropAction](handle-drop-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))

Processes [DragEvent.ACTION_DROP](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drop) event.

Updates drag state to [DragState.Finishing](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/-finishing/index.md) and invokes [onDrop](on-drop.md) callback if all preconditions are met.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DROP](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drop) action. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If event preconditions are not met. |
