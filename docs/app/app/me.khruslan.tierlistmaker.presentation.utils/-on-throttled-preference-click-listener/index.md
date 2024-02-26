//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[OnThrottledPreferenceClickListener](index.md)

# OnThrottledPreferenceClickListener

private abstract class [OnThrottledPreferenceClickListener](index.md) : [ClickThrottler](../-click-throttler/index.md), [Preference.OnPreferenceClickListener](https://developer.android.com/reference/kotlin/androidx/preference/Preference.OnPreferenceClickListener.html)

Extension of [Preference.OnPreferenceClickListener](https://developer.android.com/reference/kotlin/androidx/preference/Preference.OnPreferenceClickListener.html) that prevents multiple clicks.

Clients must override [ClickThrottler.onThrottledClick](../-click-throttler/on-throttled-click.md) instead of [onPreferenceClick](on-preference-click.md).

## Constructors

| | |
|---|---|
| [OnThrottledPreferenceClickListener](-on-throttled-preference-click-listener.md) | constructor()<br>Default no-arg constructor. |

## Functions

| Name | Summary |
|---|---|
| [onPreferenceClick](on-preference-click.md) | override fun [onPreferenceClick](on-preference-click.md)(preference: [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Invokes [ClickThrottler.onThrottledClick](../-click-throttler/on-throttled-click.md) if click is not duplicated. |
