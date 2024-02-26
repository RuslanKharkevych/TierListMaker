//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[OnThrottledPreferenceClickListener](index.md)/[onPreferenceClick](on-preference-click.md)

# onPreferenceClick

override fun [onPreferenceClick](on-preference-click.md)(preference: [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Invokes [ClickThrottler.onThrottledClick](../-click-throttler/on-throttled-click.md) if click is not duplicated.

Called when a preference has been clicked.

#### Return

True if the click was handled.

#### Parameters

| | |
|---|---|
| preference | The preference that was clicked. |
