//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[BacklogItemInserted](index.md)

# BacklogItemInserted

data class [BacklogItemInserted](index.md)(val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that backlog image was inserted at given position.

This event is sent when an image was dropped, restored or highlighted inside the backlog as a result of a drag effect.

## Constructors

| | |
|---|---|
| [BacklogItemInserted](-backlog-item-inserted.md) | constructor(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the event from item position in backlog. |

## Properties

| Name | Summary |
|---|---|
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the inserted image in the backlog. |
