//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist](../index.md)/[TierListCreator](index.md)

# TierListCreator

interface [TierListCreator](index.md)

Creator of new tier lists.

Tier list creation is asynchronous. The subclasses must ensure that it can be safely run on the main thread.

#### Inheritors

| |
|---|
| [TierListCreatorImpl](../-tier-list-creator-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [newTierList](new-tier-list.md) | abstract suspend fun [newTierList](new-tier-list.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Creates a new empty tier list. |
