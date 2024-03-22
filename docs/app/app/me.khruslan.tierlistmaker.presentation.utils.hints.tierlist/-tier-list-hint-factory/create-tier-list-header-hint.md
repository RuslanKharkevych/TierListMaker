//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintFactory](index.md)/[createTierListHeaderHint](create-tier-list-header-hint.md)

# createTierListHeaderHint

private fun [createTierListHeaderHint](create-tier-list-header-hint.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)

A helper function that creates a hint with the header of the first tier as an anchor.

If no tiers are found, the hint will be created without the anchor.

#### Return

Created hint.

#### Parameters

| | |
|---|---|
| name | The name of the hint. |
| overlay | Overlay view. |
