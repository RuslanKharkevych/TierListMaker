package me.khruslan.tierlistmaker

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.testing.CustomTestApplication
import me.khruslan.tierlistmaker.application.BaseTierListMakerApplication
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelperImpl

@CustomTestApplication(TestTierListMakerApplication::class)
interface TestApplication

open class TestTierListMakerApplication : BaseTierListMakerApplication() {

    override fun onCreate() {
        super.onCreate()
        enableLightTheme()
    }

    private fun enableLightTheme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        preferences.edit {
            putBoolean(PreferencesHelperImpl.KEY_NIGHT_MODE_ENABLED, false)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}