//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[handleNullOrUnknownAction](handle-null-or-unknown-action.md)

# handleNullOrUnknownAction

private fun [handleNullOrUnknownAction](handle-null-or-unknown-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html)?)

Processes the drag event when it's either null or unknown.

Used only to get correct error message for the exception.

#### Parameters

| | |
|---|---|
| event | Unknown drag event or null. |

#### Throws

| | |
|---|---|
| [TierListDragListener.TierListDragException](-tier-list-drag-exception/index.md) | Always throws an error with the appropriate cause message. |
