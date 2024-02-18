//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[setOnThrottledPreferenceClickListener](set-on-throttled-preference-click-listener.md)

# setOnThrottledPreferenceClickListener

fun [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html).[setOnThrottledPreferenceClickListener](set-on-throttled-preference-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Sets [OnThrottledPreferenceClickListener](-on-throttled-preference-click-listener/index.md) to the preference.

Can be used instead of [Preference.setOnPreferenceClickListener](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html#setonpreferenceclicklistener) to prevent duplicate click events.

#### Receiver

Any clickable preference.

#### Parameters

| | |
|---|---|
| onClick | Listener that must be guarded from duplicated invocations. |
