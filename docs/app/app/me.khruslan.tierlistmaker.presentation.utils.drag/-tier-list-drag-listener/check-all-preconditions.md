//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[checkAllPreconditions](check-all-preconditions.md)

# checkAllPreconditions

private fun [checkAllPreconditions](check-all-preconditions.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)

Checks if all preconditions are met.

Includes the following checks:

- Event preconditions are met.
- View must not be null.
- View must have tag of type [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md).

#### Parameters

| | |
|---|---|
| event | Drag event to validate. |
| view | View that received the drag event. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | If at least one of the preconditions wasn't met. |
