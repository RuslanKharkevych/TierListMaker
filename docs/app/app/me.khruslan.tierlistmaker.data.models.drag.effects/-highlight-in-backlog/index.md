//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[HighlightInBacklog](index.md)

# HighlightInBacklog

data class [HighlightInBacklog](index.md)(val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [HighlightEffect](../-highlight-effect/index.md)

Highlight effect of the item at given position in backlog.

This effect is produced when user hovers the drag shadow over an image inside the backlog.

## Constructors

| | |
|---|---|
| [HighlightInBacklog](-highlight-in-backlog.md) | constructor(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from the item position in backlog. |

## Properties

| Name | Summary |
|---|---|
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the item in the backlog. |
