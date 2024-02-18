//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[HighlightInTier](index.md)

# HighlightInTier

data class [HighlightInTier](index.md)(val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [HighlightEffect](../-highlight-effect/index.md)

Highlight effect of the item  at given position in tier.

This effect is produced when user hovers the drag shadow over an image inside a tier.

## Constructors

| | |
|---|---|
| [HighlightInTier](-highlight-in-tier.md) | constructor(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from the item position in tier list. |

## Properties

| Name | Summary |
|---|---|
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the item in a tier. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier in a tier list. |
