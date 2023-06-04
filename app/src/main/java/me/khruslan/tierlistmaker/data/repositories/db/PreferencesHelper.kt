package me.khruslan.tierlistmaker.data.repositories.db

import android.content.SharedPreferences

/**
 * Helper that simplifies reading and writing data from [SharedPreferences].
 */
interface PreferencesHelper {

    /**
     * Whether dark theme is preferred by user. If not - user opted for light theme.
     */
    var nightModeEnabled: Boolean

    /**
     * How many tiers will be added when a new tier list is created.
     */
    val tiersCount: Int

    /**
     * How many images will fit in a row inside a tier in a new tier list.
     */
    val scale: Int

    /**
     * The quality in percents of the new images.
     */
    val imageQuality: Int
}