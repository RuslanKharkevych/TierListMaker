//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.models](../../index.md)/[LoadingProgress](../index.md)/[Determinate](index.md)

# Determinate

data class [Determinate](index.md)(val currentItem: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val totalItems: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [LoadingProgress](../index.md)

UI state for the determinate progress of loading items.

Note that this class is immutable.

## Constructors

| | |
|---|---|
| [Determinate](-determinate.md) | constructor(currentItem: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), totalItems: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a new instance of the determinate loading progress class. |

## Properties

| Name | Summary |
|---|---|
| [currentItem](current-item.md) | val [currentItem](current-item.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Index of the item that is loading now. |
| [percent](percent.md) | val [percent](percent.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Loading progress in percentage (from 0 to 100). |
| [totalItems](total-items.md) | val [totalItems](total-items.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Total number of items that should be loaded. |
