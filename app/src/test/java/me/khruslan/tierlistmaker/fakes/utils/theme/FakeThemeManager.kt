package me.khruslan.tierlistmaker.fakes.utils.theme

import me.khruslan.tierlistmaker.utils.theme.ThemeManager

class FakeThemeManager : ThemeManager {

    var nightModeEnabled = false

    override fun setDefaultTheme() {}

    override suspend fun toggleTheme() {
        nightModeEnabled = !nightModeEnabled
    }
}