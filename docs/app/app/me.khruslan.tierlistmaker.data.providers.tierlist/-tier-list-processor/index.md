//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessor](index.md)

# TierListProcessor

interface [TierListProcessor](index.md)

Processor that converts [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md) into [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md).

Implementations might also update the tier list as a result of the effect.

#### Inheritors

| |
|---|
| [TierListProcessorImpl](../-tier-list-processor-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [processDragEffect](process-drag-effect.md) | abstract fun [processDragEffect](process-drag-effect.md)(effect: [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts drag effect into tier list event. |
| [setTierList](set-tier-list.md) | abstract fun [setTierList](set-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Attaches tier list to the processor. |
