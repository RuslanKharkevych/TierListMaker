//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[setOnPreferenceClickListener](set-on-preference-click-listener.md)

# setOnPreferenceClickListener

fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[setOnPreferenceClickListener](set-on-preference-click-listener.md)(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Sets click listener for the preference found by the key resolved from [keyResId](set-on-preference-click-listener.md).

If the preference is not found, logs error.

#### Parameters

| | |
|---|---|
| keyResId | String resource identifier of the preference key. |
| onClick | The callback to be invoked when the preference is clicked. |
