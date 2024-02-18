//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models](../index.md)/[LoadingProgress](index.md)

# LoadingProgress

sealed class [LoadingProgress](index.md)

UI state that represents a process of loading.

Could be either determinate or indeterminate.

#### Inheritors

| |
|---|
| [Determinate](-determinate/index.md) |
| [Indeterminate](-indeterminate/index.md) |

## Constructors

| | |
|---|---|
| [LoadingProgress](-loading-progress.md) | protected constructor()<br>Default empty constructor. |

## Types

| Name | Summary |
|---|---|
| [Determinate](-determinate/index.md) | data class [Determinate](-determinate/index.md)(val currentItem: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val totalItems: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [LoadingProgress](index.md)<br>UI state for the determinate progress of loading items. |
| [Indeterminate](-indeterminate/index.md) | data object [Indeterminate](-indeterminate/index.md) : [LoadingProgress](index.md)<br>UI state for the indeterminate progress of loading. |
