//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[OnThrottledClickListener](index.md)

# OnThrottledClickListener

private abstract class [OnThrottledClickListener](index.md) : [ClickThrottler](../-click-throttler/index.md), [View.OnClickListener](https://developer.android.com/reference/kotlin/android/view/View.OnClickListener.html)

Extension of [View.OnClickListener](https://developer.android.com/reference/kotlin/android/view/View.OnClickListener.html) that prevents duplicate clicks.

Clients must override [onThrottledClick](../../../../app/me.khruslan.tierlistmaker.presentation.utils/-on-throttled-click-listener/on-throttled-click.md) instead of [onClick](on-click.md).

## Constructors

| | |
|---|---|
| [OnThrottledClickListener](-on-throttled-click-listener.md) | constructor()<br>Default no-arg constructor. |

## Functions

| Name | Summary |
|---|---|
| [onClick](on-click.md) | override fun [onClick](on-click.md)(v: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>Invokes [onThrottledClick](../../../../app/me.khruslan.tierlistmaker.presentation.utils/-on-throttled-click-listener/on-throttled-click.md) if click is not duplicated. |
