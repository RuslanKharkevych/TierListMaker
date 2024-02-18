//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierChanged](index.md)

# TierChanged

data class [TierChanged](index.md)(val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that specific [Tier](../-tier/index.md) was updated.

This event is sent when images were inserted, updated or removed inside the tier as a result of a drag effect.

## Constructors

| | |
|---|---|
| [TierChanged](-tier-changed.md) | constructor(tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the event fom tier position. |

## Properties

| Name | Summary |
|---|---|
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the updated tier. |
