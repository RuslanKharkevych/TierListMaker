//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProvider](index.md)

# DefaultTierListCollectionProvider

interface [DefaultTierListCollectionProvider](index.md)

Provider of the default [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md) collection that is written to the database when user opens the application for the first time.

All functions are synchronous. All exceptions must be handled internally.

#### Inheritors

| |
|---|
| [DefaultTierListCollectionProviderImpl](../-default-tier-list-collection-provider-impl/index.md) |

## Properties

| Name | Summary |
|---|---|
| [collectionProvided](collection-provided.md) | abstract val [collectionProvided](collection-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the collection has already been provided. |

## Functions

| Name | Summary |
|---|---|
| [provideCollection](provide-collection.md) | abstract fun [provideCollection](provide-collection.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Provides a default collection and marks it as provided. |
