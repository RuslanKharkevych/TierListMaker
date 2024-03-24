//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[FeedbackFragment](index.md)/[rateApp](rate-app.md)

# rateApp

private fun [rateApp](rate-app.md)()

Opens application details in Play Market app.

In case Play Market is not installed on the device, opens application details webpage in browser. If no browsers are installed either, presents snackbar to inform user that rating the app is not possible. Logs [AppRated](../../me.khruslan.tierlistmaker.util.analytics/-app-rated/index.md) analytic event if URL was opened sucessfully.
