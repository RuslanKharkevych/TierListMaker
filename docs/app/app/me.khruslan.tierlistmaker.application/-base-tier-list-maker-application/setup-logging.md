//[app](../../../index.md)/[me.khruslan.tierlistmaker.application](../index.md)/[BaseTierListMakerApplication](index.md)/[setupLogging](setup-logging.md)

# setupLogging

private fun [setupLogging](setup-logging.md)()

Initializes logging services.

- Configures [StrictMode](https://developer.android.com/reference/kotlin/android/os/StrictMode.html) penalty logging.
- Plants [Timber.Tree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-tree/index.html).
- Registers [ActivityLifecycleLogger](../../me.khruslan.tierlistmaker.util.log.navigation/-activity-lifecycle-logger/index.md).
