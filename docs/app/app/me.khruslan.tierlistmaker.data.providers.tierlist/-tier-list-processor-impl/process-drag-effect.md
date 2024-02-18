//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)/[processDragEffect](process-drag-effect.md)

# processDragEffect

open override fun [processDragEffect](process-drag-effect.md)(effect: [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)

Converts drag effect to tier list event.

It is required to [setTierList](set-tier-list.md) before calling this function.

#### Return

Resulting tier list event.

#### Parameters

| | |
|---|---|
| effect | Drag effect to process. |
