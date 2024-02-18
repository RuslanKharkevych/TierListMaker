//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.drag](../index.md)/[TierListDragListener](index.md)

# TierListDragListener

abstract class [TierListDragListener](index.md) : [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)

A wrapper on the [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html) specific to the tier list.

Exposes drag events with the necessary [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md).

## Constructors

| | |
|---|---|
| [TierListDragListener](-tier-list-drag-listener.md) | constructor()<br>Default empty constructor. |

## Types

| Name | Summary |
|---|---|
| [TierListDragException](-tier-list-drag-exception/index.md) | private class [TierListDragException](-tier-list-drag-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Exception for the errors that could happen inside the [TierListDragListener](index.md). |

## Properties

| Name | Summary |
|---|---|
| [actionName](action-name.md) | private val [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html).[actionName](action-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Readable name of the drag event action. |
| [dragState](drag-state.md) | private var [dragState](drag-state.md): [DragState](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-state/index.md)<br>Current drag state. |

## Functions

| Name | Summary |
|---|---|
| [checkAllPreconditions](check-all-preconditions.md) | private fun [checkAllPreconditions](check-all-preconditions.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>Checks if all preconditions are met. |
| [checkEventPreconditions](check-event-preconditions.md) | private fun [checkEventPreconditions](check-event-preconditions.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Checks if clip description of the event has correct label and local state preconditions are met. |
| [checkLocalStatePreconditions](check-local-state-preconditions.md) | private fun [checkLocalStatePreconditions](check-local-state-preconditions.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Checks if event has correct local state. |
| [createImageDragDataFromClip](create-image-drag-data-from-clip.md) | private fun [createImageDragDataFromClip](create-image-drag-data-from-clip.md)(clipData: [ClipData](https://developer.android.com/reference/kotlin/android/content/ClipData.html)): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)<br>Creates image drag data from clip data. |
| [extractDragLocation](extract-drag-location.md) | private fun [extractDragLocation](extract-drag-location.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?): [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?<br>Extracts drag location from drag event and view. |
| [handleDragEndedAction](handle-drag-ended-action.md) | private fun [handleDragEndedAction](handle-drag-ended-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Processes [DragEvent.ACTION_DRAG_ENDED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_ended) event. |
| [handleDragEnteredAction](handle-drag-entered-action.md) | private fun [handleDragEnteredAction](handle-drag-entered-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>Processes [DragEvent.ACTION_DRAG_ENTERED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_entered) event. |
| [handleDragExitedAction](handle-drag-exited-action.md) | private fun [handleDragExitedAction](handle-drag-exited-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Processes [DragEvent.ACTION_DRAG_EXITED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_exited) event. |
| [handleDragLocationAction](handle-drag-location-action.md) | private fun [handleDragLocationAction](handle-drag-location-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html), view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>Processes [DragEvent.ACTION_DRAG_LOCATION](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_location) event. |
| [handleDragStartedAction](handle-drag-started-action.md) | private fun [handleDragStartedAction](handle-drag-started-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Processes [DragEvent.ACTION_DRAG_STARTED](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drag_started) event. |
| [handleDropAction](handle-drop-action.md) | private fun [handleDropAction](handle-drop-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html))<br>Processes [DragEvent.ACTION_DROP](https://developer.android.com/reference/kotlin/android/view/DragEvent.html#action_drop) event. |
| [handleNullOrUnknownAction](handle-null-or-unknown-action.md) | private fun [handleNullOrUnknownAction](handle-null-or-unknown-action.md)(event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html)?)<br>Processes the drag event when it's either null or unknown. |
| [onDrag](on-drag.md) | open override fun [onDrag](on-drag.md)(v: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, event: [DragEvent](https://developer.android.com/reference/kotlin/android/view/DragEvent.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Processes the drag event. |
| [onDragEnded](on-drag-ended.md) | abstract fun [onDragEnded](on-drag-ended.md)()<br>Called when the drag ends without dropping. |
| [onDragLocationChanged](on-drag-location-changed.md) | abstract fun [onDragLocationChanged](on-drag-location-changed.md)(dragLocation: [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?)<br>Called when the drag location changes. |
| [onDragStarted](on-drag-started.md) | abstract fun [onDragStarted](on-drag-started.md)(dragData: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Called when a new drag starts. |
| [onDrop](on-drop.md) | abstract fun [onDrop](on-drop.md)(dragData: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Called when the image is dropped in the target. |
| [onIsDraggingChanged](on-is-dragging-changed.md) | abstract fun [onIsDraggingChanged](on-is-dragging-changed.md)(isDragging: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Called when dragging starts / ends. |
