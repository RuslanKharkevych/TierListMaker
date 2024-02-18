//[app](../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](index.md)

# Package-level declarations

Data providers for styling tiers.

## Types

| Name | Summary |
|---|---|
| [TierColorProvider](-tier-color-provider/index.md) | interface [TierColorProvider](-tier-color-provider/index.md)<br>Provider of colors for tiers in the tier list. |
| [TierColorProviderImpl](-tier-color-provider-impl/index.md) | class [TierColorProviderImpl](-tier-color-provider-impl/index.md)@Injectconstructor : [TierColorProvider](-tier-color-provider/index.md)<br>[TierColorProvider](-tier-color-provider/index.md) implementation. |
| [TierNameProvider](-tier-name-provider/index.md) | interface [TierNameProvider](-tier-name-provider/index.md)<br>Provider of names for tiers in a tier list. |
| [TierNameProviderImpl](-tier-name-provider-impl/index.md) | class [TierNameProviderImpl](-tier-name-provider-impl/index.md)@Injectconstructor : [TierNameProvider](-tier-name-provider/index.md)<br>[TierNameProvider](-tier-name-provider/index.md) implementation. |
| [TierStyleProvider](-tier-style-provider/index.md) | interface [TierStyleProvider](-tier-style-provider/index.md)<br>Provider of tier styles. |
| [TierStyleProviderImpl](-tier-style-provider-impl/index.md) | class [TierStyleProviderImpl](-tier-style-provider-impl/index.md)@Injectconstructor(val colorProvider: [TierColorProvider](-tier-color-provider/index.md), val nameProvider: [TierNameProvider](-tier-name-provider/index.md), val dispatchersProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)) : [TierStyleProvider](-tier-style-provider/index.md)<br>[TierStyleProvider](-tier-style-provider/index.md) implementation. |
