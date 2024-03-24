//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](../index.md)/[FragmentNavigationLogger](index.md)/[onDestinationChanged](on-destination-changed.md)

# onDestinationChanged

open override fun [onDestinationChanged](on-destination-changed.md)(controller: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), destination: [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html), arguments: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)

Logs a new navigation destination with arguments.

Invoked when the current destination or its arguments changes. Logs info message by [Timber](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/index.html) and [ScreenShown](../../me.khruslan.tierlistmaker.util.analytics/-screen-shown/index.md) event by [analyticsService](analytics-service.md).

#### Parameters

| | |
|---|---|
| controller | The controller that navigated. |
| destination | The new destination. |
| arguments | The arguments passed to the destination. |
