//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[checkEventPreconditions](check-event-preconditions.md)

# checkEventPreconditions

private fun [checkEventPreconditions](check-event-preconditions.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))

Checks if clip description of the event has correct label and local state preconditions are met.

#### Parameters

| | |
|---|---|
| event | Drag event to validate. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If clip description label is not [ImageDragData.LABEL](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/-mapper/-l-a-b-e-l.md) or local state preconditions are not met. |
