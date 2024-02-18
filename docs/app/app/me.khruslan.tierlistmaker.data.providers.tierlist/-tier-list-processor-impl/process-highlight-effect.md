//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)/[processHighlightEffect](process-highlight-effect.md)

# processHighlightEffect

private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processHighlightEffect](process-highlight-effect.md)(effect: [HighlightEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-highlight-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)

Converts highlight effect to tier list event.

Unless the effect is [HighlightTrashBin](../../me.khruslan.tierlistmaker.data.models.drag.effects/-highlight-trash-bin/index.md), additionally inserts [targetImage](target-image.md) into the target position.

#### Receiver

Tier list that consumes the effect.

#### Return

Resulting tier list event.

#### Parameters

| | |
|---|---|
| effect | Highlight effect to process. |
