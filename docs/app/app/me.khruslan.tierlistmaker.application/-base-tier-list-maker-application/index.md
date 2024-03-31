//[app](../../../index.md)/[me.khruslan.tierlistmaker.application](../index.md)/[BaseTierListMakerApplication](index.md)

# BaseTierListMakerApplication

abstract class [BaseTierListMakerApplication](index.md) : [Application](https://developer.android.com/reference/kotlin/android/app/Application.html)

Customized application implementation for startup configurations.

This class can be used both in main module and as in instrumented tests.

#### Inheritors

| |
|---|
| [TierListMakerApplication](../-tier-list-maker-application/index.md) |

## Constructors

| | |
|---|---|
| [BaseTierListMakerApplication](-base-tier-list-maker-application.md) | constructor()<br>Default no-arg constructor. |

## Functions

| Name | Summary |
|---|---|
| [configureStrictModePenaltyLogging](configure-strict-mode-penalty-logging.md) | private fun [configureStrictModePenaltyLogging](configure-strict-mode-penalty-logging.md)()<br>Enables penalty logging of all [StrictMode](https://developer.android.com/reference/kotlin/android/os/StrictMode.html) violations in debug builds. |
| [enableThreadPolicyPenaltyLogging](enable-thread-policy-penalty-logging.md) | private fun [enableThreadPolicyPenaltyLogging](enable-thread-policy-penalty-logging.md)()<br>Enables penalty logging of all [StrictMode.ThreadPolicy](https://developer.android.com/reference/kotlin/android/os/StrictMode.ThreadPolicy.html) violations. |
| [enableVmPolicyPenaltyLogging](enable-vm-policy-penalty-logging.md) | private fun [enableVmPolicyPenaltyLogging](enable-vm-policy-penalty-logging.md)()<br>Enables penalty logging of all [StrictMode.VmPolicy](https://developer.android.com/reference/kotlin/android/os/StrictMode.VmPolicy.html) violations. |
| [getTrimMemoryLevelName](get-trim-memory-level-name.md) | private fun [getTrimMemoryLevelName](get-trim-memory-level-name.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Converts trim memory level value to a readable format. |
| [onConfigurationChanged](on-configuration-changed.md) | open override fun [onConfigurationChanged](on-configuration-changed.md)(newConfig: [Configuration](https://developer.android.com/reference/kotlin/android/content/res/Configuration.html))<br>Logs configuration changes. |
| [onCreate](on-create.md) | open override fun [onCreate](on-create.md)()<br>Performs global initialization tasks. |
| [onLowMemory](on-low-memory.md) | open override fun [onLowMemory](on-low-memory.md)()<br>Logs low memory events. |
| [onTrimMemory](on-trim-memory.md) | open override fun [onTrimMemory](on-trim-memory.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Logs trim memory events. |
| [performInitializationTasks](perform-initialization-tasks.md) | private fun [performInitializationTasks](perform-initialization-tasks.md)()<br>Initializes global components. |
| [plantTimberTree](plant-timber-tree.md) | private fun [plantTimberTree](plant-timber-tree.md)()<br>Plants [Timber.Tree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-tree/index.html) for logging in debug build and reporting crashes in release builds. |
| [setupLogging](setup-logging.md) | private fun [setupLogging](setup-logging.md)()<br>Initializes logging services. |
