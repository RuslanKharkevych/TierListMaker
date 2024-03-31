package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.utils.setPreferenceSummary

/**
 * Fragment that represents "About" section in the navigation drawer.
 *
 * Consists of only "App version" section for now.
 *
 * @constructor Default no-arg constructor.
 */
class AboutFragment : PreferenceFragmentCompat() {

    /**
     * Inflates preferences XML and populates app version value.
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
        setPreferencesFromResource(R.xml.preferences_about, rootKey)
        setPreferenceSummary(R.string.pref_app_version_key, BuildConfig.VERSION_NAME)
    }
}