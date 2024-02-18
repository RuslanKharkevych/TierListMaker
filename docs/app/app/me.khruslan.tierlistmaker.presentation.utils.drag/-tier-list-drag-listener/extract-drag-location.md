//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[extractDragLocation](extract-drag-location.md)

# extractDragLocation

private fun [extractDragLocation](extract-drag-location.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?): [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?

Extracts drag location from drag event and view.

#### Return

Extracted location or null if drag target is unavailable.

#### Parameters

| | |
|---|---|
| event | The event with [DragEvent.ACTION_DRAG_LOCATION](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_location) action. |
| view | View that received the drag event. |
