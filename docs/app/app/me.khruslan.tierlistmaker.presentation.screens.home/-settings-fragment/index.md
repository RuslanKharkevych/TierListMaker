//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[SettingsFragment](index.md)

# SettingsFragment

class [SettingsFragment](index.md) : [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html)

Fragment that represents &quot;Settings&quot; section in the navigation drawer.

Contains &quot;Initial number of tiers&quot;, &quot;Default scale&quot; and &quot;Image quality&quot; seek bar preferences. Preference values are connected with keys via XML and automatically updated by the fragment.

## Constructors

| | |
|---|---|
| [SettingsFragment](-settings-fragment.md) | constructor()<br>Default no-arg constructor. |

## Functions

| Name | Summary |
|---|---|
| [onCreatePreferences](on-create-preferences.md) | open override fun [onCreatePreferences](on-create-preferences.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?, rootKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)<br>Inflates preferences XML. |
