//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[RemoveLastFromTier](index.md)

# RemoveLastFromTier

data class [RemoveLastFromTier](index.md)(val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveEffect](../-remove-effect/index.md)

Remove effect of the last item in tier.

This effect is produced when drag target changes so that the last image in tier is no longer highlighted.

## Constructors

| | |
|---|---|
| [RemoveLastFromTier](-remove-last-from-tier.md) | constructor(tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from tier position. |

## Properties

| Name | Summary |
|---|---|
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier. |
