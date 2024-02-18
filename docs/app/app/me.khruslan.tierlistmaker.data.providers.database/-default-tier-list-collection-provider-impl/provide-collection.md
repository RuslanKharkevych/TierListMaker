//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProviderImpl](index.md)/[provideCollection](provide-collection.md)

# provideCollection

open override fun [provideCollection](provide-collection.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;

Provides a default collection and marks it as provided.

Attempts to create all tier lists from [tierListParamsList](tier-list-params-list.md). If failed to create a list due to an error, skips it. The operation is traced with [ProvideDefaultTierListCollectionTrace](../../me.khruslan.tierlistmaker.util.performance/-provide-default-tier-list-collection-trace/index.md).

#### Return

List of created tier lists.
