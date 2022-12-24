package me.khruslan.tierlistmaker.utils.theme

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import javax.inject.Inject

/**
 * Implementation of [ThemeManager].
 *
 * @property preferencesHelper helper class for accessing [SharedPreferences].
 * @property dispatcherProvider provider of [CoroutineDispatcher].
 */
class ThemeManagerImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val dispatcherProvider: DispatcherProvider
) : ThemeManager {

    /**
     * Applies light or dark theme based on [nightModeEnabled] flag. Can cause configuration change
     * to update resources if theme has been updated.
     *
     * @param nightModeEnabled if true - dark theme, else - light theme.
     */
    private fun applyTheme(nightModeEnabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (nightModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
    }

    override fun setDefaultTheme() {
        applyTheme(preferencesHelper.nightModeEnabled)
    }

    override suspend fun toggleTheme() {
        withContext(dispatcherProvider.io) {
            val nightModeEnabled = preferencesHelper.nightModeEnabled
            withContext(dispatcherProvider.main) {
                applyTheme(!nightModeEnabled)
                preferencesHelper.nightModeEnabled = !nightModeEnabled
            }
        }
    }
}