//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[setOnThrottledClickListener](set-on-throttled-click-listener.md)

# setOnThrottledClickListener

fun [View](https://developer.android.com/reference/kotlin/android/view/View.html).[setOnThrottledClickListener](set-on-throttled-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Sets [OnThrottledClickListener](-on-throttled-click-listener/index.md) to the view.

Can be used instead of [View.setOnClickListener](https://developer.android.com/reference/kotlin/android/view/View.html#setonclicklistener) to prevent duplicate click events.

#### Receiver

Any clickable view.

#### Parameters

| | |
|---|---|
| onClick | Listener that must be guarded from duplicated invocations. |
