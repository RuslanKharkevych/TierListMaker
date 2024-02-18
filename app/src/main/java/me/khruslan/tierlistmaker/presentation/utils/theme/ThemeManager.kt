package me.khruslan.tierlistmaker.presentation.utils.theme

/**
 * Manager for changing application's theme at runtime.
 *
 * Supports two themes: light and dark. Allows to toggle between them in runtime. Saves user theme
 * preference.
 */
interface ThemeManager {

    /**
     * Synchronously default application's theme.
     *
     * The default theme is the one previously preferred by user. If it's the first application
     * launch, applies the system theme.
     */
    fun setDefaultTheme()

    /**
     * Asynchronously toggles light/dark theme.
     *
     * Applies changes and saves user preference.
     */
    suspend fun toggleTheme()
}