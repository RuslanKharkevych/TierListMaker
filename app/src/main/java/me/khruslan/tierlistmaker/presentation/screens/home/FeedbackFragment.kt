package me.khruslan.tierlistmaker.presentation.screens.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.google.android.material.snackbar.Snackbar
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.utils.FeedbackUtils
import me.khruslan.tierlistmaker.presentation.utils.setOnPreferenceClickListener
import timber.log.Timber

/**
 * Fragment that represents "Feedback" section in the navigation drawer.
 *
 * Consists of "Contact us", "Report bug" and "Rate app" sections.
 *
 * @constructor Default no-arg constructor.
 */
class FeedbackFragment : PreferenceFragmentCompat() {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * Application ID read from the build configuration.
         *
         * Uniquely identifies the app on the device and in the Google Play Store.
         */
        private const val APPLICATION_ID: String = BuildConfig.APPLICATION_ID

        /**
         * A link to the app at Google Play Store.
         */
        private const val APP_DETAILS_PLAY_MARKET_URL = "market://details?id=$APPLICATION_ID"

        /**
         * A fallback browser link to the app.
         */
        private const val APP_DETAILS_BROWSER_URL =
            "https://play.google.com/store/apps/details?id=$APPLICATION_ID"
    }

    /**
     * Inflates preferences XML and initializes click listeners.
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
        setPreferencesFromResource(R.xml.preferences_feedback, rootKey)
        initClickListeners()
    }

    /**
     * Initializes click listeners for preferences: "Contact us", "Report bug" and "Rate app".
     */
    private fun initClickListeners() {
        setOnPreferenceClickListener(R.string.pref_contact_us_key, ::sendFeedback)
        setOnPreferenceClickListener(R.string.pref_report_bug_key, ::reportBug)
        setOnPreferenceClickListener(R.string.pref_rate_app_key, ::rateApp)
    }

    /**
     * Opens an email application with prefilled recipient and subject for sending a feedback.
     */
    private fun sendFeedback() {
        FeedbackUtils.sendFeedback(requireActivity())
    }

    /**
     * Opens an email application with prefilled recipient, subject and message for reporting a bug.
     */
    private fun reportBug() {
        FeedbackUtils.reportIssue(requireActivity())
    }

    /**
     * Opens application details in Play Market app.
     *
     * In case Play Market is not installed on the device, opens application details webpage in
     * browser. If no browsers are installed either, presents snackbar to inform user that rating
     * the app is not possible.
     */
    private fun rateApp() {
        try {
            Timber.i("Opening the application in Play Market")
            openUrl(APP_DETAILS_PLAY_MARKET_URL)
        } catch (pme: ActivityNotFoundException) {
            try {
                Timber.w(pme, "Activity not found. Opening the application in browser")
                openUrl(APP_DETAILS_BROWSER_URL)
            } catch (be: ActivityNotFoundException) {
                Timber.e(be, "Activity not found. Presenting no apps found snackbar")
                presentNoAppsFoundSnackbar()
            }
        }
    }

    /**
     * Launches intent to open URL.
     *
     * @param url URL to open.
     * @throws [ActivityNotFoundException] If no applications found that can open this URL.
     */
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
        Timber.i("Started activity with intent: $intent")
    }

    /**
     * Presents snackbar to inform user that rating the app is not possible.
     */
    private fun presentNoAppsFoundSnackbar() {
        Snackbar
            .make(requireView(), R.string.snackbar_msg_no_rate_apps_found, Snackbar.LENGTH_LONG)
            .show()
    }
}