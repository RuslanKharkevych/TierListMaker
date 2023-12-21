package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import me.khruslan.tierlistmaker.R

/**
 * [PreferenceFragmentCompat] that represents "Settings" section in the navigation drawer.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}