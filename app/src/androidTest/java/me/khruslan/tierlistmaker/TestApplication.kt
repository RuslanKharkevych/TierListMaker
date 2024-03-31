package me.khruslan.tierlistmaker

import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.testing.CustomTestApplication
import me.khruslan.tierlistmaker.application.BaseTierListMakerApplication

@CustomTestApplication(TestTierListMakerApplication::class)
interface TestApplication

open class TestTierListMakerApplication : BaseTierListMakerApplication() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}