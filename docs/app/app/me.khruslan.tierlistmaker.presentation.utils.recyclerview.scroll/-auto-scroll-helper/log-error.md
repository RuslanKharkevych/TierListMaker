//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollHelper](index.md)/[logError](log-error.md)

# logError

private fun [logError](log-error.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), strategy: [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md), location: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md))

Logs error that can happen during [shouldScroll](should-scroll.md) calculations.

Includes all passed parameters in error message.

#### Parameters

| | |
|---|---|
| position | Item position in layout manager. |
| strategy | Direction strategy. |
| location | Target location. |
