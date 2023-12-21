package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.utils.setPreferenceSummary

/**
 * [PreferenceFragmentCompat] that represents "About" section in the navigation drawer.
 */
class AboutFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_about, rootKey)
        setPreferenceSummary(R.string.pref_app_version_key, BuildConfig.VERSION_NAME)
    }
}