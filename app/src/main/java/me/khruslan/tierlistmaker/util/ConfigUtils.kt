package me.khruslan.tierlistmaker.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.getSystemService
import me.khruslan.tierlistmaker.R

/**
 * Utilities that provide information about device configuration.
 */
object ConfigUtils {

    /**
     * Get current language of the application.
     *
     * This function checks for the current used string resources to determine the correct language.
     *
     * @param context Context to get resources.
     * @return The language code.
     */
    fun getAppLanguage(context: Context): String {
        return context.getString(R.string.app_language)
    }

    /**
     * Get the preferred language of the device.
     *
     * On Android 7+ returns the first language from the locale list. On older versions returns the
     * language of the primary locale.
     *
     * @return The language code.
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
     * Refer to [DisplayMetrics.density] for the information about this property.
     *
     * @return The logical density of the display.
     */
    fun getDisplayDensity(): Float {
        return Resources.getSystem().displayMetrics.density
    }

    /**
     * Get display resolution in pixels.
     *
     * On Android 11+ returns bounds of the maximum window metrics. On older versions returns real
     * size of the default display.
     *
     * @param context Context to obtain window manager.
     * @return Display resolution string in {width}x{height} format, or "unknown" if unable to
     * access window metrics.
     */
    fun getDisplayResolution(context: Context): String {
        val windowManager: WindowManager = context.getSystemService() ?: return "unknown"
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val bounds = windowManager.maximumWindowMetrics.bounds
            "${bounds.width()}x${bounds.height()}"
        } else {
            val point = Point()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getRealSize(point)
            "${point.x}x${point.y}"
        }
    }

    /**
     * Get current user preference for the scaling factor for fonts.
     *
     * Refer to  [Configuration.fontScale] for the information about this property.
     *
     * @param context Context to get resources.
     * @return Scaling factor for fonts.
     */
    fun getFontScale(context: Context): Float {
        return context.resources.configuration.fontScale
    }
}