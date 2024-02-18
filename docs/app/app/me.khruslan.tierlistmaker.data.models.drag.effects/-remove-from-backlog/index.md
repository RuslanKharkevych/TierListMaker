//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[RemoveFromBacklog](index.md)

# RemoveFromBacklog

data class [RemoveFromBacklog](index.md)(val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveEffect](../-remove-effect/index.md)

Remove effect of the item at given position in backlog.

This effect is produced when user starts drag from backlog or when drag target changes so that backlog image is no longer highlighted.

## Constructors

| | |
|---|---|
| [RemoveFromBacklog](-remove-from-backlog.md) | constructor(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from item position in backlog. |

## Properties

| Name | Summary |
|---|---|
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the item in the backlog. |
