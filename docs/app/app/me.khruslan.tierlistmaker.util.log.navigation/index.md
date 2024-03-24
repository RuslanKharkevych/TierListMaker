//[app](../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](index.md)

# Package-level declarations

Utilities for logging navigation events.

## Types

| Name | Summary |
|---|---|
| [ActivityLifecycleLogger](-activity-lifecycle-logger/index.md) | class [ActivityLifecycleLogger](-activity-lifecycle-logger/index.md) : [Application.ActivityLifecycleCallbacks](https://developer.android.com/reference/kotlin/android/app/Application.ActivityLifecycleCallbacks.html)<br>Logs lifecycle callbacks of all activities. |
| [DialogEventLogger](-dialog-event-logger/index.md) | private class [DialogEventLogger](-dialog-event-logger/index.md)(val logTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DialogInterface.OnShowListener](https://developer.android.com/reference/kotlin/android/content/DialogInterface.OnShowListener.html), [DialogInterface.OnDismissListener](https://developer.android.com/reference/kotlin/android/content/DialogInterface.OnDismissListener.html)<br>Logs dialog events (when dialog is shown or dismissed). |
| [DrawerStateLogger](-drawer-state-logger/index.md) | class [DrawerStateLogger](-drawer-state-logger/index.md) : [DrawerLayout.DrawerListener](https://developer.android.com/reference/kotlin/androidx/drawerlayout/widget/DrawerLayout.DrawerListener.html)<br>Logs drawer state events (opened / closed). |
| [FragmentNavigationLogger](-fragment-navigation-logger/index.md) | class [FragmentNavigationLogger](-fragment-navigation-logger/index.md)(val analyticsService: [AnalyticsService](../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)) : [NavController.OnDestinationChangedListener](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.OnDestinationChangedListener.html)<br>Logs fragment destination changes. |

## Functions

| Name | Summary |
|---|---|
| [setLogTag](set-log-tag.md) | fun [AlertDialog](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AlertDialog.html).[setLogTag](set-log-tag.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AlertDialog](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AlertDialog.html)<br>Binds [DialogEventLogger](-dialog-event-logger/index.md) to the alert dialog. |
