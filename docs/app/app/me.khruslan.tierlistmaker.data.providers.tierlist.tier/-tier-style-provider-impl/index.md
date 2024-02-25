//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierStyleProviderImpl](index.md)

# TierStyleProviderImpl

class [TierStyleProviderImpl](index.md) @Inject constructor(val colorProvider: [TierColorProvider](../-tier-color-provider/index.md), val nameProvider: [TierNameProvider](../-tier-name-provider/index.md), val dispatchersProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)) : [TierStyleProvider](../-tier-style-provider/index.md)

[TierStyleProvider](../-tier-style-provider/index.md) implementation.

Moves tier styles generation to the background thread.

## Constructors

| | |
|---|---|
| [TierStyleProviderImpl](-tier-style-provider-impl.md) | @Inject <br>constructor(colorProvider: [TierColorProvider](../-tier-color-provider/index.md), nameProvider: [TierNameProvider](../-tier-name-provider/index.md), dispatchersProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md))<br>Creates a new tier style provider instance. |

## Properties

| Name | Summary |
|---|---|
| [colorProvider](color-provider.md) | private val [colorProvider](color-provider.md): [TierColorProvider](../-tier-color-provider/index.md)<br>Provides tier colors. |
| [dispatchersProvider](dispatchers-provider.md) | private val [dispatchersProvider](dispatchers-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides default dispatcher. |
| [nameProvider](name-provider.md) | private val [nameProvider](name-provider.md): [TierNameProvider](../-tier-name-provider/index.md)<br>Provides tier names. |

## Functions

| Name | Summary |
|---|---|
| [getTierStyles](get-tier-styles.md) | open suspend override fun [getTierStyles](get-tier-styles.md)(size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TierStyle](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-style/index.md)&gt;<br>Generates list of tier styles with given size. |
