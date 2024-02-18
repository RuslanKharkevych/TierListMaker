//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListImageHolder](index.md)/[startDragCompat](start-drag-compat.md)

# startDragCompat

private fun [startDragCompat](start-drag-compat.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to start drag that supports all versions.

Starting from [Build.VERSION_CODES.N](https://developer.android.com/reference/kotlin/android/os/Build.VERSION_CODES.html#n) the shadow will be fully opaque.

#### Return

Whether the drag was started successfully.

#### Parameters

| | |
|---|---|
| view | View that has [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html) set. |
| data | Data of the image that will be dragged. |
