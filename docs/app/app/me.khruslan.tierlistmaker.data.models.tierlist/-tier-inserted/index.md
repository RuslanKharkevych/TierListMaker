//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierInserted](index.md)

# TierInserted

data class [TierInserted](index.md)(val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that the new [Tier](../-tier/index.md) was inserted at given position.

This event is sent when the new tier was added by user.

## Constructors

| | |
|---|---|
| [TierInserted](-tier-inserted.md) | constructor(tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the event from tier position. |

## Properties

| Name | Summary |
|---|---|
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the inserted tier. |
