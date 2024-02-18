//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[ClickThrottler](index.md)

# ClickThrottler

private abstract class [ClickThrottler](index.md)

Implements throttling mechanism to prevent duplicated click events.

Unlike [View.OnClickListener](https://developer.android.com/reference/kotlin/android/view/View.OnClickListener.html), the throttler is not meant to be shared between multiple views.

#### Inheritors

| |
|---|
| [OnThrottledClickListener](../-on-throttled-click-listener/index.md) |
| [OnThrottledPreferenceClickListener](../-on-throttled-preference-click-listener/index.md) |

## Constructors

| | |
|---|---|
| [ClickThrottler](-click-throttler.md) | constructor()<br>Default no-arg constructor. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [lastClickTimestamp](last-click-timestamp.md) | private var [lastClickTimestamp](last-click-timestamp.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The timestamp of the last click. |

## Functions

| Name | Summary |
|---|---|
| [onThrottledClick](on-throttled-click.md) | abstract fun [onThrottledClick](on-throttled-click.md)()<br>Invoked when click event is allowed. |
| [throttleClick](throttle-click.md) | protected fun [throttleClick](throttle-click.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Throttles the click event. |
