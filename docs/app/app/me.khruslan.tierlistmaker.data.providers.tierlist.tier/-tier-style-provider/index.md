//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierStyleProvider](index.md)

# TierStyleProvider

interface [TierStyleProvider](index.md)

Provider of tier styles.

The styles are asynchronously provided for all tiers at once. The subclasses must ensure that it can be safely called from the main thread.

#### Inheritors

| |
|---|
| [TierStyleProviderImpl](../-tier-style-provider-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [getTierStyles](get-tier-styles.md) | abstract suspend fun [getTierStyles](get-tier-styles.md)(size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TierStyle](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-style/index.md)&gt;<br>Generates list of tier styles with given size. |
