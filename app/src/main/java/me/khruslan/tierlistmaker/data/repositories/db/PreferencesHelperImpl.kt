package me.khruslan.tierlistmaker.data.repositories.db

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import me.khruslan.tierlistmaker.R
import javax.inject.Inject

/**
 * Implementation of [PreferencesHelper].
 *
 * @property context application context.
 */
class PreferencesHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesHelper {

    /**
     * Companion object of [PreferencesHelperImpl] that stores preferences file name and keys.
     */
    companion object {
        private const val PREFS_NAME = "app_preferences"
        private const val KEY_NIGHT_MODE_ENABLED = "dark_mode_enabled"
    }

    /**
     * Singleton instance of [SharedPreferences].
     */
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override var nightModeEnabled
        get() = preferences.getBoolean(
            KEY_NIGHT_MODE_ENABLED,
            context.resources.getBoolean(R.bool.night_mode_enabled)
        )
        set(value) = preferences.edit {
            putBoolean(KEY_NIGHT_MODE_ENABLED, value)
        }
}