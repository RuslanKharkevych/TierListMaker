package me.khruslan.tierlistmaker.util.log.navigation

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import timber.log.Timber

/**
 * Logs lifecycle callbacks of all activities.
 *
 * Should be registered in the application.
 *
 * @constructor Creates a new activity lifecycle logger.
 */
class ActivityLifecycleLogger : ActivityLifecycleCallbacks {

    /**
     * Logs a message indicating that activity has been created.
     *
     * Called when an activity calls [Activity.onCreate].
     *
     * @param activity Activity that was created.
     * @param savedInstanceState Saved state bundle or null.
     */
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

    /**
     * Logs a message indicating that activity has been started.
     *
     * Called when an activity calls [Activity.onStart].
     *
     * @param activity Activity that was started.
     */
    override fun onActivityStarted(activity: Activity) {
        Timber.i("${activity::class.simpleName} started")
    }

    /**
     * Logs a message indicating that activity has been resumed.
     *
     * Called when an activity calls [Activity.onResume].
     *
     * @param activity Activity that was resumed.
     */
    override fun onActivityResumed(activity: Activity) {
        Timber.i("${activity::class.simpleName} resumed")
    }

    /**
     * Logs a message indicating that activity has been paused.
     *
     * Called when an activity calls [Activity.onPause].
     *
     * @param activity Activity that was paused.
     */
    override fun onActivityPaused(activity: Activity) {
        Timber.i("${activity::class.simpleName} paused")
    }

    /**
     * Logs a message indicating that activity has been stopped.
     *
     * Called when an activity calls [Activity.onStop].
     *
     * @param activity Activity that was stopped.
     */
    override fun onActivityStopped(activity: Activity) {
        Timber.i("${activity::class.simpleName} stopped")
    }

    /**
     * Logs a message indicating that activity's instance state has been saved.
     *
     * Called when an activity calls [Activity.onSaveInstanceState].
     *
     * @param activity Activity that saved instance state.
     * @param outState Saved state bundle.
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.i("${activity::class.simpleName} saved instance state: $outState")
    }

    /**
     * Logs a message indicating that activity has been destroyed.
     *
     * Called when an activity calls [Activity.onDestroy].
     *
     * @param activity Activity that was destroyed.
     */
    override fun onActivityDestroyed(activity: Activity) {
        Timber.i("${activity::class.simpleName} destroyed")
    }
}