//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierListPreviewAdapter](index.md)/[onItemMove](on-item-move.md)

# onItemMove

open override fun [onItemMove](on-item-move.md)(fromPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Swap previews and notifies that data set has changed.

Also invokes [onPreviewMoved](on-preview-moved.md) callback to allow clients to add additional event handling logic.

#### Parameters

| | |
|---|---|
| fromPosition | Initial position of the tier. |
| toPosition | Target position of the tier. |
