package me.khruslan.tierlistmaker.utils.log

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.google.firebase.ktx.Firebase
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.utils.extensions.getDeviceLanguage
import timber.log.Timber

/**
 * [Timber.Tree] implementation for release builds. Captures non-fatal exceptions and sends them
 * to the [Firebase.crashlytics].
 */
class ReleaseTree(context: Context) : Timber.Tree() {

    init {
        setCustomKeys(context)
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == Log.ERROR || priority == Log.WARN
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
            key(CrashlyticsKeys.APP_LANGUAGE, context.getString(R.string.app_language))
            key(CrashlyticsKeys.DEVICE_LANGUAGE, Resources.getSystem().getDeviceLanguage())
            key(CrashlyticsKeys.DISPLAY_DENSITY, Resources.getSystem().displayMetrics.density)
        }
    }
}