//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProviderImpl](index.md)

# DefaultTierListCollectionProviderImpl

class [DefaultTierListCollectionProviderImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val preferencesHelper: [PreferencesHelper](../-preferences-helper/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [DefaultTierListCollectionProvider](../-default-tier-list-collection-provider/index.md)

[DefaultTierListCollectionProvider](../-default-tier-list-collection-provider/index.md) implementation.

Images for the collection are read from assets. The performance of the operation is traced.

## Constructors

| | |
|---|---|
| [DefaultTierListCollectionProviderImpl](-default-tier-list-collection-provider-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), preferencesHelper: [PreferencesHelper](../-preferences-helper/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md))<br>Creates a new provider instance. |

## Types

| Name | Summary |
|---|---|
| [AssetsNotFoundException](-assets-not-found-exception/index.md) | private class [AssetsNotFoundException](-assets-not-found-exception/index.md) : [IOException](https://developer.android.com/reference/kotlin/java/io/IOException.html)<br>Thrown when assets are expected but not found in certain location. |
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Assets path constants for internal usage. |
| [TierListParams](-tier-list-params/index.md) | private data class [TierListParams](-tier-list-params/index.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html) val titleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val folderName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Parameters for creating a tier list. |

## Properties

| Name | Summary |
|---|---|
| [collectionProvided](collection-provided.md) | open override val [collectionProvided](collection-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the collection has already been provided. |
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Context for reading resources and assets. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Manages performance traces. |
| [preferencesHelper](preferences-helper.md) | private val [preferencesHelper](preferences-helper.md): [PreferencesHelper](../-preferences-helper/index.md)<br>Persists [collectionProvided](collection-provided.md) flag. |
| [tierListParamsList](tier-list-params-list.md) | private val [tierListParamsList](tier-list-params-list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DefaultTierListCollectionProviderImpl.TierListParams](-tier-list-params/index.md)&gt;<br>List of parameters needed for creating default tier list collection. |

## Functions

| Name | Summary |
|---|---|
| [buildTier](build-tier.md) | private fun [buildTier](build-tier.md)(images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)<br>Builds tier and populates it with images. |
| [buildTierList](build-tier-list.md) | private fun [buildTierList](build-tier-list.md)(params: [DefaultTierListCollectionProviderImpl.TierListParams](-tier-list-params/index.md)): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?<br>Builds tier list from params. |
| [buildTiers](build-tiers.md) | private fun [buildTiers](build-tiers.md)(zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;<br>Builds tiers and populates them with images. |
| [provideCollection](provide-collection.md) | open override fun [provideCollection](provide-collection.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Provides a default collection and marks it as provided. |
| [readImages](read-images.md) | private fun [readImages](read-images.md)(folderName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Reads all assets from the provided folder. |
