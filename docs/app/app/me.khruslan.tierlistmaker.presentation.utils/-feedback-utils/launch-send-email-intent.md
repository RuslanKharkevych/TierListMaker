//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[FeedbackUtils](index.md)/[launchSendEmailIntent](launch-send-email-intent.md)

# launchSendEmailIntent

private fun [launchSendEmailIntent](launch-send-email-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

Opens an email application with prefilled recipient, subject and (optionally) message for reporting an issue.

#### Parameters

| | |
|---|---|
| context | Activity context. |
| subject | Email subject to prefill. |
| message | Email message to prefill (optional). |

#### Throws

| | |
|---|---|
| [ActivityNotFoundException](https://developer.android.com/reference/kotlin/android/content/ActivityNotFoundException.html) | If case no email applications found on device. |
