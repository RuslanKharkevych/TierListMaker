//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)/[processUpdateEffect](process-update-effect.md)

# processUpdateEffect

private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processUpdateEffect](process-update-effect.md)(effect: [UpdateEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-update-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)

Converts update effect to tier list event.

Unless the effect is [ThrowToTrashBin](../../me.khruslan.tierlistmaker.data.models.drag.effects/-throw-to-trash-bin/index.md), additionally updates the image in the tier list.

#### Receiver

Tier list that consumes the effect.

#### Return

Resulting tier list event.

#### Parameters

| | |
|---|---|
| effect | Update effect to process. |
