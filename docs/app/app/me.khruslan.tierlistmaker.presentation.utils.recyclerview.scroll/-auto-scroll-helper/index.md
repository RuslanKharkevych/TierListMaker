//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollHelper](index.md)

# AutoScrollHelper

class [AutoScrollHelper](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val layoutManager: [LinearLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/LinearLayoutManager.html))

Helper that checks whether automatic scrolling should be performed depending on the current target location.

#### Parameters

| | |
|---|---|
| context | Context for resolving resources. |

## Constructors

| | |
|---|---|
| [AutoScrollHelper](-auto-scroll-helper.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), layoutManager: [LinearLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/LinearLayoutManager.html))<br>Creates a new auto scroll helper for the supplied layout manager. |

## Types

| Name | Summary |
|---|---|
| [AutoScrollHelperException](-auto-scroll-helper-exception/index.md) | private class [AutoScrollHelperException](-auto-scroll-helper-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Exception for errors that can happen during autoscroll calculations. |
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |
| [DirectionStrategy](-direction-strategy/index.md) | private interface [DirectionStrategy](-direction-strategy/index.md)<br>Strategy used by [shouldScroll](should-scroll.md) algorithm. |
| [Down](-down/index.md) | private inner class [Down](-down/index.md) : [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md)<br>Direction strategy for checking if view should scroll down. |
| [Up](-up/index.md) | private inner class [Up](-up/index.md) : [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md)<br>Direction strategy for checking if view should scroll up. |

## Properties

| Name | Summary |
|---|---|
| [autoScrollRegionHeightPx](auto-scroll-region-height-px.md) | private val [autoScrollRegionHeightPx](auto-scroll-region-height-px.md): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>The height of the auto scroll region in pixels. |
| [layoutManager](layout-manager.md) | private val [layoutManager](layout-manager.md): [LinearLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/LinearLayoutManager.html)<br>Layout manager of the recycler view with auto scrolling functionality. |

## Functions

| Name | Summary |
|---|---|
| [getVisibleHeight](get-visible-height.md) | private fun [View](https://developer.android.com/reference/kotlin/android/view/View.html).[getVisibleHeight](get-visible-height.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Calculates height of the global visible rectangle of the given view. |
| [logError](log-error.md) | private fun [logError](log-error.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), strategy: [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md), location: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md))<br>Logs error that can happen during [shouldScroll](should-scroll.md) calculations. |
| [shouldScroll](should-scroll.md) | private fun [shouldScroll](should-scroll.md)(strategy: [AutoScrollHelper.DirectionStrategy](-direction-strategy/index.md), location: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if view should scroll to the given direction. |
| [shouldScrollDown](should-scroll-down.md) | fun [shouldScrollDown](should-scroll-down.md)(targetLocation: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if view should scroll down. |
| [shouldScrollUp](should-scroll-up.md) | fun [shouldScrollUp](should-scroll-up.md)(targetLocation: [TargetLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-target-location/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if view should scroll up. |
