package me.khruslan.tierlistmaker.utils.extensions

import android.content.res.Resources
import android.os.Build

/**
 * Get the preferred language of the device.
 *
 * @receiver resources obtained from [Resources.getSystem].
 * @return the language code.
 */
fun Resources.getDeviceLanguage(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        configuration.locales[0].language
    } else {
        @Suppress("DEPRECATION")
        configuration.locale.language
    }
}

