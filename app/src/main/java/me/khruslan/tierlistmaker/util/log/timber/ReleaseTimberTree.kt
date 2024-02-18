package me.khruslan.tierlistmaker.util.log.timber

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.Firebase
import me.khruslan.tierlistmaker.util.ConfigUtils
import me.khruslan.tierlistmaker.util.log.CrashlyticsKeys
import timber.log.Timber

/**
 * [Timber.Tree] implementation for release builds.
 *
 * Captures non-fatal exceptions and sends them to the [Firebase.crashlytics].
 *
 * @param context Application context.
 * @constructor Creates a new release tree.
 */
class ReleaseTimberTree(context: Context) : Timber.Tree() {

    init {
        setCustomKeys(context)
    }

    /**
     * Returns whether a message with given priority should be logged.
     *
     * All messages with [Log.INFO] level and higher are logged.
     *
     * @param tag Tag of the log. Ignored.
     * @param priority Priority of the log.
     * @return Whether a message should be logged.
     */
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }

    /**
     * Write a log message to its destination.
     *
     * If exception is not null, records a report to send to Crashlytics. Otherwise logs a message
     * that will be included in the next report. Called for all level-specific methods by default.
     *
     * @param priority Log level. Always ignored.
     * @param tag Explicit or inferred tag. Always ignored.
     * @param message Formatted log message.
     * @param t Accompanying exception. May be null.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t != null) {
            Firebase.crashlytics.recordException(t)
        } else {
            Firebase.crashlytics.log(message)
        }
    }

    /**
     * Sets custom [Firebase.crashlytics] keys.
     *
     * For information about the exact keys, refer to [CrashlyticsKeys].
     *
     * @param context Application context.
     */
    private fun setCustomKeys(context: Context) {
        Firebase.crashlytics.setCustomKeys {
            key(CrashlyticsKeys.APP_LANGUAGE, ConfigUtils.getAppLanguage(context))
            key(CrashlyticsKeys.DEVICE_LANGUAGE, ConfigUtils.getDeviceLanguage())
            key(CrashlyticsKeys.DISPLAY_DENSITY, ConfigUtils.getDisplayDensity())
            key(CrashlyticsKeys.DISPLAY_RESOLUTION, ConfigUtils.getDisplayResolution(context))
            key(CrashlyticsKeys.FONT_SCALE, ConfigUtils.getFontScale(context))
        }
    }
}