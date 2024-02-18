package me.khruslan.tierlistmaker.presentation.utils

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.net.toUri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.util.capitalized
import me.khruslan.tierlistmaker.util.log.navigation.setLogTag
import timber.log.Timber

/**
 * Utility that provides user interface for sending feedback.
 *
 * Allows to open an email application with prefilled information for reporting an issue or sending
 * a feedback.
 */
object FeedbackUtils {

    /**
     * A URI scheme for email addresses.
     *
     * Set as a data of the "Compose an email" intents.
     */
    private const val MAILTO_URI_SCHEME = "mailto:"

    /**
     * The person that is intended to receive feedback emails.
     *
     * This email is prefilled in the “to” field in the email client.
     */
    private const val RECIPIENT_EMAIL = "ruslan.kharkevych@gmail.com"

    /**
     * The log tag of the alert, shown by [showSendUsEmailAlert] method.
     */
    private const val SEND_US_EMAIL_ALERT_LOG_TAG = "SendUsEmailAlert"

    /**
     * Opens an email application with prefilled recipient, subject and message for reporting
     * an issue.
     *
     * In case no email applications found on the device, shows an alert asking to manually send an
     * email.
     *
     * @param context Activity context.
     */
    fun reportIssue(context: Context) {
        try {
            Timber.i("Launching report issue intent")
            launchSendEmailIntent(
                context = context,
                subject = context.getString(R.string.bug_report_email_subject),
                message = buildBugReportInfo(context)
            )
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "Activity not found, showing Send Us Email alert")
            showSendUsEmailAlert(context)
        }
    }

    /**
     * Opens an email application with prefilled recipient and subject for sending a feedback.
     *
     * In case no email applications found on the device, shows an alert asking to manually send an
     * email.
     *
     * @param context Activity context.
     */
    fun sendFeedback(context: Context) {
        try {
            Timber.i("Launching send feedback intent")
            launchSendEmailIntent(
                context = context,
                subject = context.getString(R.string.send_feedback_email_subject)
            )
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "Activity not found, showing Send Us Email alert")
            showSendUsEmailAlert(context)
        }
    }

    /**
     * Opens an email application with prefilled recipient, subject and (optionally) message for
     * reporting an issue.
     *
     * @param context Activity context.
     * @param subject Email subject to prefill.
     * @param message Email message to prefill (optional).
     * @throws [ActivityNotFoundException] If case no email applications found on device.
     */
    private fun launchSendEmailIntent(context: Context, subject: String, message: String? = null) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = MAILTO_URI_SCHEME.toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(RECIPIENT_EMAIL))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        context.startActivity(intent)
        Timber.i("Started activity with intent: $intent")
    }

    /**
     * Builds a string with environment information for reporting a bug.
     *
     * Includes application version, device model and operating system.
     *
     * @param context Activity context.
     * @return Report info string.
     */
    private fun buildBugReportInfo(context: Context): String {
        return with(context) {
            StringBuilder()
                .appendLine(getString(R.string.bug_report_info_version, BuildConfig.VERSION_NAME))
                .appendLine(getString(R.string.bug_report_info_device, getDeviceModel()))
                .appendLine(getString(R.string.bug_report_info_os, Build.VERSION.RELEASE))
                .appendLine()
                .toString()
        }
    }

    /**
     * Returns device model in user-friendly format, e.g. Samsung SM-M325FV.
     *
     * @return Device model.
     */
    private fun getDeviceModel(): String {
        return if (Build.MODEL.startsWith(Build.MANUFACTURER, ignoreCase = true)) {
            Build.MODEL
        } else {
            "${Build.MANUFACTURER} ${Build.MODEL}"
        }.capitalized()
    }

    /**
     * Shows an alert asking to manually send a bug report with a "Copy email" button that allows
     * to copy [RECIPIENT_EMAIL] to the clipboard.
     *
     * @param context Activity context.
     */
    private fun showSendUsEmailAlert(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.send_us_email_alert_title, RECIPIENT_EMAIL))
            .setPositiveButton(R.string.btn_copy_email) { _, _ ->
                copyTextToClipboard(context, RECIPIENT_EMAIL)
                Timber.i("Copied email to clipboard")
            }
            .setNegativeButton(R.string.btn_cancel, null)
            .create()
            .setLogTag(SEND_US_EMAIL_ALERT_LOG_TAG)
            .show()
    }

    /**
     * Copies text to clipboard.
     *
     * Starting from [Build.VERSION_CODES.TIRAMISU] the system shows a default UI to users when text
     * is copied. On older devices a custom toast is shown.
     *
     * @param context Activity context.
     * @param text Text to copy.
     */
    private fun copyTextToClipboard(context: Context, text: String) {
        val clipboardService = context.getSystemService(Context.CLIPBOARD_SERVICE)
        val clipboardManager = clipboardService as? ClipboardManager ?: return
        val clip = ClipData.newPlainText(null, text)
        clipboardManager.setPrimaryClip(clip)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(
                context,
                context.getString(R.string.toast_msg_text_copied, text),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}