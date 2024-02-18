//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](../index.md)/[ActivityLifecycleLogger](index.md)

# ActivityLifecycleLogger

class [ActivityLifecycleLogger](index.md) : [Application.ActivityLifecycleCallbacks](https://developer.android.com/reference/kotlin/android/app/Application.ActivityLifecycleCallbacks.html)

Logs lifecycle callbacks of all activities.

Should be registered in the application.

## Constructors

| | |
|---|---|
| [ActivityLifecycleLogger](-activity-lifecycle-logger.md) | constructor()<br>Creates a new activity lifecycle logger. |

## Functions

| Name | Summary |
|---|---|
| [onActivityCreated](on-activity-created.md) | open override fun [onActivityCreated](on-activity-created.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Logs a message indicating that activity has been created. |
| [onActivityDestroyed](on-activity-destroyed.md) | open override fun [onActivityDestroyed](on-activity-destroyed.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html))<br>Logs a message indicating that activity has been destroyed. |
| [onActivityPaused](on-activity-paused.md) | open override fun [onActivityPaused](on-activity-paused.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html))<br>Logs a message indicating that activity has been paused. |
| [onActivityResumed](on-activity-resumed.md) | open override fun [onActivityResumed](on-activity-resumed.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html))<br>Logs a message indicating that activity has been resumed. |
| [onActivitySaveInstanceState](on-activity-save-instance-state.md) | open override fun [onActivitySaveInstanceState](on-activity-save-instance-state.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), outState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html))<br>Logs a message indicating that activity's instance state has been saved. |
| [onActivityStarted](on-activity-started.md) | open override fun [onActivityStarted](on-activity-started.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html))<br>Logs a message indicating that activity has been started. |
| [onActivityStopped](on-activity-stopped.md) | open override fun [onActivityStopped](on-activity-stopped.md)(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html))<br>Logs a message indicating that activity has been stopped. |
