package me.khruslan.tierlistmaker.utils.theme

/**
 * Manager for changing application's theme at runtime.
 */
interface ThemeManager {

    /**
     * Applies default application's theme (previously preferred by user or the system one).
     */
    fun setDefaultTheme()

    /**
     * Toggles light/dark theme. Applies changes and saves user preference.
     */
    suspend fun toggleTheme()
}