package me.khruslan.tierlistmaker.utils.log.timber

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.Firebase
import me.khruslan.tierlistmaker.utils.ConfigUtils
import me.khruslan.tierlistmaker.utils.log.CrashlyticsKeys
import timber.log.Timber

/**
 * [Timber.Tree] implementation for release builds. Captures non-fatal exceptions and sends them
 * to the [Firebase.crashlytics].
 */
class ReleaseTimberTree(context: Context) : Timber.Tree() {

    init {
        setCustomKeys(context)
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }

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
     * @param context application's context.
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