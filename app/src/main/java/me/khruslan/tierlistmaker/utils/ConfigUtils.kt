package me.khruslan.tierlistmaker.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import me.khruslan.tierlistmaker.R

/**
 * Utilities that provide information about device configuration.
 */
object ConfigUtils {

    /**
     * Get current language of the application.
     *
     * @param context context to get resources.
     * @return the language code.
     */
    fun getAppLanguage(context: Context): String {
        return context.getString(R.string.app_language)
    }

    /**
     * Get the preferred language of the device.
     *
     * @return the language code.
     */
    fun getDeviceLanguage(): String {
        val configuration = Resources.getSystem().configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales[0].language
        } else {
            @Suppress("DEPRECATION")
            configuration.locale.language
        }
    }

    /**
     * Get density of the device's display.
     *
     * @return the logical density of the display.
     */
    fun getDisplayDensity(): Float {
        return Resources.getSystem().displayMetrics.density
    }
}