//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)/[processRemoveEffect](process-remove-effect.md)

# processRemoveEffect

private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processRemoveEffect](process-remove-effect.md)(effect: [RemoveEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-remove-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)

Converts remove effect to tier list event.

Unless the effect is [UnhighlightTrashBin](../../me.khruslan.tierlistmaker.data.models.drag.effects/-unhighlight-trash-bin/index.md), additionally removes the target image.

#### Receiver

Tier list that consumes the effect.

#### Return

Resulting tier list event.

#### Parameters

| | |
|---|---|
| effect | Remove effect to process. |
