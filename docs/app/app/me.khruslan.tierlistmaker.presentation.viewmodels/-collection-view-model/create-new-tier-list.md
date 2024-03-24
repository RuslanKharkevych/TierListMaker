//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[createNewTierList](create-new-tier-list.md)

# createNewTierList

fun [createNewTierList](create-new-tier-list.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Asynchronously creates an empty tier list.

Triggers [tierListCreatedEvent](tier-list-created-event.md) and logs [TierListCreated](../../me.khruslan.tierlistmaker.util.analytics/-tier-list-created/index.md) analytic event.

#### Parameters

| | |
|---|---|
| title | Name of the tier list. |
