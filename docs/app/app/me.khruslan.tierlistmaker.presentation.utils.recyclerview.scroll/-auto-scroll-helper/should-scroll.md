//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollHelper](index.md)/[shouldScroll](should-scroll.md)

# shouldScroll

private fun [shouldScroll](should-scroll.md)(strategy: [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md), location: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Checks if view should scroll to the given direction.

Loops through visible views to calculate distance to target (from top or bottom depending on the strategy). Compares resulting distance with [autoScrollRegionHeightPx](auto-scroll-region-height-px.md) to determine if target is within autoscroll region.

#### Return

Whether auto scrolling should be performed. Returns false if calculation was unsuccessful due to unexpected error.

#### Parameters

| | |
|---|---|
| strategy | Direction strategy (up or down). |
| location | Current location of the target. |
