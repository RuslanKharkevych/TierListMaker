package me.khruslan.tierlistmaker.presentation.utils.theme

import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import timber.log.Timber
import javax.inject.Inject

/**
 * [ThemeManager] implementation.
 *
 * @property preferencesHelper Saves user theme preference.
 * @property dispatcherProvider Provides coroutine dispatchers.
 * @constructor Creates theme manager with injected dependencies.
 */
class ThemeManagerImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val dispatcherProvider: DispatcherProvider
) : ThemeManager {

    /**
     * Applies light or dark theme based on [nightModeEnabled] flag.
     *
     * Can cause configuration change to update resources if theme has been updated.
     *
     * @param nightModeEnabled If true - dark theme, else - light theme.
     */
    private fun applyTheme(nightModeEnabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (nightModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
        Timber.i("Applied theme. Night mode: $nightModeEnabled")
    }

    /**
     * Synchronously applies default application's theme.
     *
     * The default theme is synchronously fetched from shared preferences.
     */
    override fun setDefaultTheme() {
        applyTheme(preferencesHelper.nightModeEnabled)
    }

    /**
     * Asynchronously toggles light/dark theme.
     *
     * Applies changes and saves user preference.
     */
    override suspend fun toggleTheme() {
        Timber.i("Changing theme")
        withContext(dispatcherProvider.io) {
            val nightModeEnabled = preferencesHelper.nightModeEnabled
            withContext(dispatcherProvider.main) {
                applyTheme(!nightModeEnabled)
            }
            preferencesHelper.nightModeEnabled = !nightModeEnabled
        }
    }
}