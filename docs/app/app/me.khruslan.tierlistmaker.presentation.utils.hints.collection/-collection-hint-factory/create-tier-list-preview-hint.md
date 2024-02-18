//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintFactory](index.md)/[createTierListPreviewHint](create-tier-list-preview-hint.md)

# createTierListPreviewHint

private fun [createTierListPreviewHint](create-tier-list-preview-hint.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)

A helper function that creates a hint with tier list card as an anchor.

If tier list cart is not found, the hint will be created without the anchor.

#### Return

Created hint.

#### Parameters

| | |
|---|---|
| name | The name of the hint. |
| overlay | Overlay view. |
