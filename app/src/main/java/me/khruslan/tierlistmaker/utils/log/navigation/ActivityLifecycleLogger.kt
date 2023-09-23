package me.khruslan.tierlistmaker.utils.log.navigation

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import timber.log.Timber

/**
 * Logs lifecycle callbacks of all activities.
 */
class ActivityLifecycleLogger : ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.i(
            buildString {
                append("${activity::class.simpleName} created")
                if (savedInstanceState != null) {
                    append(" with saved instance state: $savedInstanceState")
                }
            }
        )
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.i("${activity::class.simpleName} started")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.i("${activity::class.simpleName} resumed")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.i("${activity::class.simpleName} paused")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.i("${activity::class.simpleName} stopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.i("${activity::class.simpleName} saved instance state: $outState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.i("${activity::class.simpleName} destroyed")
    }
}