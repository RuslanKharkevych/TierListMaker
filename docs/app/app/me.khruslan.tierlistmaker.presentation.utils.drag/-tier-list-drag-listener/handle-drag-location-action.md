//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleDragLocationAction](handle-drag-location-action.md)

# handleDragLocationAction

private fun [handleDragLocationAction](handle-drag-location-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)

Processes [DragEvent.ACTION_DRAG_LOCATION](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_location) event.

Invokes [onDragLocationChanged](on-drag-location-changed.md) callback if all preconditions are met.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_LOCATION](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_location) action. |
| view | View that received the drag event. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If preconditions are not met. |
