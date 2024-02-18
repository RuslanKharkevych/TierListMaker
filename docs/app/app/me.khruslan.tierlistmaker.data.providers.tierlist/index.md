//[app](../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](index.md)

# Package-level declarations

Data providers for creating / updating tier list.

## Types

| Name | Summary |
|---|---|
| [TierListCreator](-tier-list-creator/index.md) | interface [TierListCreator](-tier-list-creator/index.md)<br>Creator of new tier lists. |
| [TierListCreatorImpl](-tier-list-creator-impl/index.md) | class [TierListCreatorImpl](-tier-list-creator-impl/index.md)@Injectconstructor(val dispatcherProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)) : [TierListCreator](-tier-list-creator/index.md)<br>[TierListCreator](-tier-list-creator/index.md) implementation. |
| [TierListProcessor](-tier-list-processor/index.md) | interface [TierListProcessor](-tier-list-processor/index.md)<br>Processor that converts [DragEffect](../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md) into [TierListEvent](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md). |
| [TierListProcessorImpl](-tier-list-processor-impl/index.md) | class [TierListProcessorImpl](-tier-list-processor-impl/index.md)@Injectconstructor : [TierListProcessor](-tier-list-processor/index.md)<br>[TierListProcessor](-tier-list-processor/index.md) implementation. |
