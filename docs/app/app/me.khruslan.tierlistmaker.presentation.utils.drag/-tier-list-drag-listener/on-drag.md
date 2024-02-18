//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)/[onDrag](on-drag.md)

# onDrag

open override fun [onDrag](on-drag.md)(v: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Processes the drag event.

On success may invoke the appropriate callback. On error logs it and resets the drag state.

#### Return

Whether the drag event was handled successfully.

#### Parameters

| | |
|---|---|
| v | The view that received the drag event. |
| event | The event object for the drag event. |
