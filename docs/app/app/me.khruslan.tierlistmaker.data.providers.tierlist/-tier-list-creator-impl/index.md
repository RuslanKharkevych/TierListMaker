//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListCreatorImpl](index.md)

# TierListCreatorImpl

class [TierListCreatorImpl](index.md)@Injectconstructor(val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)) : [TierListCreator](../-tier-list-creator/index.md)

[TierListCreator](../-tier-list-creator/index.md) implementation.

Moves tier list creation to the background thread.

## Constructors

| | |
|---|---|
| [TierListCreatorImpl](-tier-list-creator-impl.md) | @Inject<br>constructor(dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md))<br>Creates a new tier list creator instance. |

## Properties

| Name | Summary |
|---|---|
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides IO dispatcher. |
| [preferencesHelper](preferences-helper.md) | private val [preferencesHelper](preferences-helper.md): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Fetches zoom value and tiers count. |

## Functions

| Name | Summary |
|---|---|
| [getTiersCount](get-tiers-count.md) | private suspend fun [getTiersCount](get-tiers-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Asynchronously fetches number of tiers from user preferences. |
| [getZoomValue](get-zoom-value.md) | private suspend fun [getZoomValue](get-zoom-value.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Asynchronously fetches scale from user preferences and converts it to the zoom value of the tier list. |
| [newTierList](new-tier-list.md) | open suspend override fun [newTierList](new-tier-list.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Creates a new empty tier list. |
