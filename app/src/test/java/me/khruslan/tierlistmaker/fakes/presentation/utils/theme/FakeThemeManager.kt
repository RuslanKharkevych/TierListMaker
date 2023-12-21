package me.khruslan.tierlistmaker.fakes.presentation.utils.theme

import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager

class FakeThemeManager : ThemeManager {

    var nightModeEnabled = false

    override fun setDefaultTheme() {}

    override suspend fun toggleTheme() {
        nightModeEnabled = !nightModeEnabled
    }
}