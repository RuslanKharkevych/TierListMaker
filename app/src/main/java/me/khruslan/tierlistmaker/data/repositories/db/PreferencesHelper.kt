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
}