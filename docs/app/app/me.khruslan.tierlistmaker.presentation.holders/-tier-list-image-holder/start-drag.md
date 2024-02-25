//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListImageHolder](index.md)/[startDrag](start-drag.md)

# startDrag

private fun [startDrag](start-drag.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)

Attempts to start a drag.

This functions validates the view tag.

#### Parameters

| | |
|---|---|
| view | View that should be dragged. |

#### Throws

| | |
|---|---|
| [TierListImageHolder.TierListImageException](-tier-list-image-exception/index.md) | When view is null or has incompatible tag. |
<br>
---
<br>

private fun [startDrag](start-drag.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))

Attempts to start a drag.

If drag started successfully, logs the information about the drag data.

#### Parameters

| | |
|---|---|
| view | View that should be dragged. |
| data | Payload of the dragged item. |

#### Throws

| | |
|---|---|
| [TierListImageHolder.TierListImageException](-tier-list-image-exception/index.md) | If unable to start drag. |
