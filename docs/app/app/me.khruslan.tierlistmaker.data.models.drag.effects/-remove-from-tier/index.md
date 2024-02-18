//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[RemoveFromTier](index.md)

# RemoveFromTier

data class [RemoveFromTier](index.md)(val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveEffect](../-remove-effect/index.md)

Remove effect of the item at given position in tier.

This effect is produced when user starts drag from tier or when drag target changes so that tier image is no longer highlighted.

## Constructors

| | |
|---|---|
| [RemoveFromTier](-remove-from-tier.md) | constructor(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from item position in the tier list. |

## Properties

| Name | Summary |
|---|---|
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the item in a tier. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier in a tier list. |
