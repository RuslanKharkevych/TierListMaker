//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)/[processInsertEffect](process-insert-effect.md)

# processInsertEffect

private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processInsertEffect](process-insert-effect.md)(effect: [InsertEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-insert-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)

Converts insert effect to tier list event.

Additionally inserts the image into the tier list.

#### Receiver

Tier list that consumes the effect.

#### Return

Resulting tier list event.

#### Parameters

| | |
|---|---|
| effect | Insert effect to process. |
