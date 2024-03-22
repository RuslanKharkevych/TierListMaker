//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintFactory](index.md)/[create](create.md)

# create

open override fun [create](create.md)(step: [TierListHintStep](../-tier-list-hint-step/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)

Creates a tier list hint for the provided step.

When adding or removing steps, make sure to update overlay views to show/hide previous and next buttons depending on the step index.

#### Return

Created hint.

#### Parameters

| | |
|---|---|
| step | Step of the hint. |
