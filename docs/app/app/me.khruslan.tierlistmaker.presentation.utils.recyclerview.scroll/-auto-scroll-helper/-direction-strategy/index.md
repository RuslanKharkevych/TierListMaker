//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../../index.md)/[AutoScrollHelper](../index.md)/[DirectionStrategy](index.md)

# DirectionStrategy

private interface [DirectionStrategy](index.md)

Strategy used by [shouldScroll](../should-scroll.md) algorithm.

Can be either [Up](../-up/index.md) or [Down](../-down/index.md) depending on the scroll direction. Provides item positions that must be looped through in order to calculate distance to target. Describes how to calculate distance to target withing the item view.

#### Inheritors

| |
|---|
| [Up](../-up/index.md) |
| [Down](../-down/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateDistanceToTarget](calculate-distance-to-target.md) | abstract fun [calculateDistanceToTarget](calculate-distance-to-target.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), targetOffset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Calculates distance to target withing the item view. |
| [getItemPositions](get-item-positions.md) | abstract fun [getItemPositions](get-item-positions.md)(targetPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)<br>Provides progression of the item positions that must be looped through in order to calculate distance to target. |
