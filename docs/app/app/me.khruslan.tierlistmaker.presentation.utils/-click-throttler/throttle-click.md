//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[ClickThrottler](index.md)/[throttleClick](throttle-click.md)

# throttleClick

protected fun [throttleClick](throttle-click.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Throttles the click event.

If the click is allowed, returns true and invokes [onThrottledClick](on-throttled-click.md) listener. Otherwise returns false without taking action. The click is blocked if elapsed time from the last click is less than [MIN_CLICK_INTERVAL_MILLIS](-constants/-m-i-n_-c-l-i-c-k_-i-n-t-e-r-v-a-l_-m-i-l-l-i-s.md).

#### Return

Whether the click event was allowed.
