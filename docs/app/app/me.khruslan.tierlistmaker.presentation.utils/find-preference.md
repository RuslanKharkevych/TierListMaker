//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[findPreference](find-preference.md)

# findPreference

private fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[findPreference](find-preference.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html)

Finds preference by the key resolved from [keyResId](find-preference.md).

#### Return

Found preference.

#### Parameters

| | |
|---|---|
| keyResId | String resource identifier of the preference key. |

#### Throws

| | |
|---|---|
| [PreferenceNotFoundException](-preference-not-found-exception/index.md) | When preference was not found. |
