package me.khruslan.tierlistmaker.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
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

    /**
     * Get display resolution in pixels.
     *
     * @param context context to obtain window manager.
     * @return display resolution string in {width}x{height} format, or "unknown" if unable to
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
     * @param context context to get resources.
     * @return scaling factor for fonts.
     */
    fun getFontScale(context: Context): Float {
        return context.resources.configuration.fontScale
    }
}