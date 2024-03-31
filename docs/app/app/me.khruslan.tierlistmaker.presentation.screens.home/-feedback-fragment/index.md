//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[FeedbackFragment](index.md)

# FeedbackFragment

class [FeedbackFragment](index.md) : [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html)

Fragment that represents &quot;Feedback&quot; section in the navigation drawer.

Consists of &quot;Contact us&quot;, &quot;Report bug&quot; and &quot;Rate app&quot; sections.

## Constructors

| | |
|---|---|
| [FeedbackFragment](-feedback-fragment.md) | constructor()<br>Default no-arg constructor. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | internal object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [analyticsService](analytics-service.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>lateinit var [analyticsService](analytics-service.md): [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)<br>Service for logging analytic events. |

## Functions

| Name | Summary |
|---|---|
| [initClickListeners](init-click-listeners.md) | private fun [initClickListeners](init-click-listeners.md)()<br>Initializes click listeners for preferences: &quot;Contact us&quot;, &quot;Report bug&quot; and &quot;Rate app&quot;. |
| [onCreatePreferences](on-create-preferences.md) | open override fun [onCreatePreferences](on-create-preferences.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?, rootKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)<br>Inflates preferences XML and initializes click listeners. |
| [openUrl](open-url.md) | private fun [openUrl](open-url.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Launches intent to open URL. |
| [presentNoAppsFoundSnackbar](present-no-apps-found-snackbar.md) | private fun [presentNoAppsFoundSnackbar](present-no-apps-found-snackbar.md)()<br>Presents snackbar to inform user that rating the app is not possible. |
| [rateApp](rate-app.md) | private fun [rateApp](rate-app.md)()<br>Opens application details in Play Market app. |
| [reportBug](report-bug.md) | private fun [reportBug](report-bug.md)()<br>Opens an email application with prefilled recipient, subject and message for reporting a bug. |
| [sendFeedback](send-feedback.md) | private fun [sendFeedback](send-feedback.md)()<br>Opens an email application with prefilled recipient and subject for sending a feedback. |
