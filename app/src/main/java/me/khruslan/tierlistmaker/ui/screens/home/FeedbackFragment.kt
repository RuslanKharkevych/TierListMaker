package me.khruslan.tierlistmaker.ui.screens.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.utils.view.FeedbackUtils
import me.khruslan.tierlistmaker.utils.view.setOnPreferenceClickListener

/**
 * [PreferenceFragmentCompat] that represents "Feedback" section in the navigation drawer.
 */
class FeedbackFragment : PreferenceFragmentCompat() {

    /**
     * Companion object of [FeedbackFragment] used for storing constants.
     */
    private companion object {
        private const val APPLICATION_ID = BuildConfig.APPLICATION_ID
        private const val APP_DETAILS_PLAY_MARKET_URL = "market://details?id=$APPLICATION_ID"
        private const val APP_DETAILS_BROWSER_URL =
            "https://play.google.com/store/apps/details?id=$APPLICATION_ID"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_feedback, rootKey)
        initClickListeners()
    }

    /**
     * Initializes click listeners for preferences: "Contact us", "Report bug" and "Rate app".
     */
    private fun initClickListeners() {
        setOnPreferenceClickListener(R.string.pref_contact_us_key) { sendFeedback() }
        setOnPreferenceClickListener(R.string.pref_report_bug_key) { reportBug() }
        setOnPreferenceClickListener(R.string.pref_rate_app_key) { rateApp() }
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
     * Opens application details in Play Market app or in browser in case Play Market is not
     * installed on the device. If no browsers are installed either, presents snackbar to inform
     * user that rating the app is not possible.
     */
    private fun rateApp() {
        try {
            openUrl(APP_DETAILS_PLAY_MARKET_URL)
        } catch (_: ActivityNotFoundException) {
            try {
                openUrl(APP_DETAILS_BROWSER_URL)
            } catch (_: ActivityNotFoundException) {
                presentNoAppsFoundSnackbar()
            }
        }
    }

    /**
     * Launches intent to open URL.
     *
     * @param url URL to open.
     * @throws [ActivityNotFoundException] if no applications found that can open this URL.
     */
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
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