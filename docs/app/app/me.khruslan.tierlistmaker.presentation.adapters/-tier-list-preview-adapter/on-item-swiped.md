//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierListPreviewAdapter](index.md)/[onItemSwiped](on-item-swiped.md)

# onItemSwiped

open override fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Invokes [onPreviewSwiped](on-preview-swiped.md) callback.

User should be asked to confirm preview deletion. On confirmation call [removePreview](remove-preview.md) to update the data set.

#### Parameters

| | |
|---|---|
| position | Position of the swiped preview. |
