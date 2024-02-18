//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[SettingsFragment](index.md)/[onCreatePreferences](on-create-preferences.md)

# onCreatePreferences

open override fun [onCreatePreferences](on-create-preferences.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?, rootKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)

Inflates preferences XML.

Called during [onCreate](../../../../app/me.khruslan.tierlistmaker.presentation.screens.home/-settings-fragment/on-create.md) to supply the preferences for this fragment.

#### Parameters

| | |
|---|---|
| savedInstanceState | If the fragment is being re-created from a previous saved state, this is the state. |
| rootKey | If non-null, this preference fragment should be rooted at the [PreferenceScreen](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceScreen.html) with this key. |
