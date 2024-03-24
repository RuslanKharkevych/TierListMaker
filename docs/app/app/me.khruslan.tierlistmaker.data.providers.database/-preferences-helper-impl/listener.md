//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[PreferencesHelperImpl](index.md)/[listener](listener.md)

# listener

private val [listener](listener.md): [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html)

Shared preference change listener that logs all updates.

Also logs [PreferenceChanged](../../me.khruslan.tierlistmaker.util.analytics/-preference-changed/index.md) analytic event. It must be a class field (see the documentation of [SharedPreferences.registerOnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html#registeronsharedpreferencechangelistener)).
