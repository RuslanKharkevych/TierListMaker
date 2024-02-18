//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListProcessorImpl](index.md)

# TierListProcessorImpl

class [TierListProcessorImpl](index.md)@Injectconstructor : [TierListProcessor](../-tier-list-processor/index.md)

[TierListProcessor](../-tier-list-processor/index.md) implementation.

## Constructors

| | |
|---|---|
| [TierListProcessorImpl](-tier-list-processor-impl.md) | @Inject<br>constructor()<br>Creates a new tier list processor instance. |

## Properties

| Name | Summary |
|---|---|
| [targetImage](target-image.md) | private val [targetImage](target-image.md): [ResourceImage](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-resource-image/index.md)<br>Resource image that represents a drag target. |
| [tierList](tier-list.md) | private lateinit var [tierList](tier-list.md): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Tier list on which the processor operates. |

## Functions

| Name | Summary |
|---|---|
| [processDragEffect](process-drag-effect.md) | open override fun [processDragEffect](process-drag-effect.md)(effect: [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts drag effect to tier list event. |
| [processHighlightEffect](process-highlight-effect.md) | private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processHighlightEffect](process-highlight-effect.md)(effect: [HighlightEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-highlight-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts highlight effect to tier list event. |
| [processInsertEffect](process-insert-effect.md) | private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processInsertEffect](process-insert-effect.md)(effect: [InsertEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-insert-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts insert effect to tier list event. |
| [processRemoveEffect](process-remove-effect.md) | private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processRemoveEffect](process-remove-effect.md)(effect: [RemoveEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-remove-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts remove effect to tier list event. |
| [processUpdateEffect](process-update-effect.md) | private fun [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).[processUpdateEffect](process-update-effect.md)(effect: [UpdateEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-update-effect/index.md)): [TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)<br>Converts update effect to tier list event. |
| [setTierList](set-tier-list.md) | open override fun [setTierList](set-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Attaches tier list to the processor. |
