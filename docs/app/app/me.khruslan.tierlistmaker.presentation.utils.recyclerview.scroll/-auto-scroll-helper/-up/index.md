//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../../index.md)/[AutoScrollHelper](../index.md)/[Up](index.md)

# Up

private inner class [Up](index.md) : [AutoScrollHelper.DirectionStrategy](../-direction-strategy/index.md)

Direction strategy for checking if view should scroll up.

## Constructors

| | |
|---|---|
| [Up](-up.md) | constructor()<br>Creates a new direction strategy. |

## Functions

| Name | Summary |
|---|---|
| [calculateDistanceToTarget](calculate-distance-to-target.md) | open override fun [calculateDistanceToTarget](calculate-distance-to-target.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), targetOffset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Calculates distance to target withing the item view. |
| [getItemPositions](get-item-positions.md) | open override fun [getItemPositions](get-item-positions.md)(targetPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [IntProgression](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-progression/index.html)<br>Provides progression from the first visible item position to the target position. |
