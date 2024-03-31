package me.khruslan.tierlistmaker.application

import dagger.hilt.android.HiltAndroidApp
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import javax.inject.Inject

/**
 * Custom application class required by [Hilt](https://dagger.dev/hilt) for generating
 * [Dagger](https://dagger.dev) components.
 *
 * This class contains only logic that is tied with dependency injection. All other startup
 * configurations are extened from [BaseTierListMakerApplication]. This decoupling is requied
 * because it's not allowed to use [HiltAndroidApp] and [Inject] annotations in instrumented tests.
 *
 * @constructor Default constructor called by Android system.
 */
@HiltAndroidApp
class TierListMakerApplication : BaseTierListMakerApplication() {

    /**
     * Manager used to set the application theme.
     *
     * Needs to be injected inside the application because updating the theme leads to configuration
     * change. Therefore, it must be done before any activity is created to avoid UI glitches.
     */
    @Inject
    lateinit var themeManager: ThemeManager

    /**
     * Applies the application theme.
     *
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     */
    override fun onCreate() {
        super.onCreate()
        themeManager.setDefaultTheme()
    }
}