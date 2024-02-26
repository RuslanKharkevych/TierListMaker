package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import me.khruslan.tierlistmaker.R

/**
 * Fragment that represents "Settings" section in the navigation drawer.
 *
 * Contains "Initial number of tiers", "Default scale" and "Image quality" seek bar preferences.
 * Preference values are connected with keys via XML and automatically updated by the fragment.
 *
 * @constructor Default no-arg constructor.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    /**
     * Inflates preferences XML.
     *
     * Called during [PreferenceFragmentCompat.onCreate] to supply the preferences for this
     * fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @param rootKey If non-null, this preference fragment should be rooted at the
     * [PreferenceScreen] with this key.
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}