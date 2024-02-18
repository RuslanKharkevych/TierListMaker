//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProvider](index.md)/[provideCollection](provide-collection.md)

# provideCollection

abstract fun [provideCollection](provide-collection.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;

Provides a default collection and marks it as provided.

If collection can't be provided due to an error, returns empty list. Regardless of the operation result, [collectionProvided](collection-provided.md) flag must be updated to true.

#### Return

List of created tier lists.
