//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[setPreferenceSummary](set-preference-summary.md)

# setPreferenceSummary

fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[setPreferenceSummary](set-preference-summary.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), summary: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Sets summary for the preference found by the key resolved from [keyResId](set-preference-summary.md).

If the preference is not found, logs error.

#### Parameters

| | |
|---|---|
| keyResId | String resource identifier of the preference key. |
| summary | Preference summary. |
