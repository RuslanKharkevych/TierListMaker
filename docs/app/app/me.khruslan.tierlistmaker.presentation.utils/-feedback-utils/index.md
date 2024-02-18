//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[FeedbackUtils](index.md)

# FeedbackUtils

object [FeedbackUtils](index.md)

Utility that provides user interface for sending feedback.

Allows to open an email application with prefilled information for reporting an issue or sending a feedback.

## Properties

| Name | Summary |
|---|---|
| [MAILTO_URI_SCHEME](-m-a-i-l-t-o_-u-r-i_-s-c-h-e-m-e.md) | private const val [MAILTO_URI_SCHEME](-m-a-i-l-t-o_-u-r-i_-s-c-h-e-m-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A URI scheme for email addresses. |
| [RECIPIENT_EMAIL](-r-e-c-i-p-i-e-n-t_-e-m-a-i-l.md) | private const val [RECIPIENT_EMAIL](-r-e-c-i-p-i-e-n-t_-e-m-a-i-l.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The person that is intended to receive feedback emails. |
| [SEND_US_EMAIL_ALERT_LOG_TAG](-s-e-n-d_-u-s_-e-m-a-i-l_-a-l-e-r-t_-l-o-g_-t-a-g.md) | private const val [SEND_US_EMAIL_ALERT_LOG_TAG](-s-e-n-d_-u-s_-e-m-a-i-l_-a-l-e-r-t_-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The log tag of the alert, shown by [showSendUsEmailAlert](show-send-us-email-alert.md) method. |

## Functions

| Name | Summary |
|---|---|
| [buildBugReportInfo](build-bug-report-info.md) | private fun [buildBugReportInfo](build-bug-report-info.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Builds a string with environment information for reporting a bug. |
| [copyTextToClipboard](copy-text-to-clipboard.md) | private fun [copyTextToClipboard](copy-text-to-clipboard.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Copies text to clipboard. |
| [getDeviceModel](get-device-model.md) | private fun [getDeviceModel](get-device-model.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns device model in user-friendly format, e.g. Samsung SM-M325FV. |
| [launchSendEmailIntent](launch-send-email-intent.md) | private fun [launchSendEmailIntent](launch-send-email-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)<br>Opens an email application with prefilled recipient, subject and (optionally) message for reporting an issue. |
| [reportIssue](report-issue.md) | fun [reportIssue](report-issue.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Opens an email application with prefilled recipient, subject and message for reporting an issue. |
| [sendFeedback](send-feedback.md) | fun [sendFeedback](send-feedback.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Opens an email application with prefilled recipient and subject for sending a feedback. |
| [showSendUsEmailAlert](show-send-us-email-alert.md) | private fun [showSendUsEmailAlert](show-send-us-email-alert.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Shows an alert asking to manually send a bug report with a &quot;Copy email&quot; button that allows to copy [RECIPIENT_EMAIL](-r-e-c-i-p-i-e-n-t_-e-m-a-i-l.md) to the clipboard. |
