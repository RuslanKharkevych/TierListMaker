package me.khruslan.tierlistmaker

import android.app.Application
import android.content.res.Configuration
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper
import me.khruslan.tierlistmaker.utils.log.navigation.ActivityLifecycleLogger
import me.khruslan.tierlistmaker.utils.log.timber.DebugTimberTree
import me.khruslan.tierlistmaker.utils.log.timber.ReleaseTimberTree
import me.khruslan.tierlistmaker.utils.theme.ThemeManager
import timber.log.Timber
import javax.inject.Inject

/**
 * Customized [Application] implementation for startup configurations.
 */
@HiltAndroidApp
class TierListMakerApplication : Application() {

    /**
     * Manager used to set default theme.
     */
    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate() {
        super.onCreate()

        configureStrictModePenaltyLogging()
        plantTimberTree()
        Timber.i("Application created")
        registerActivityLifecycleCallbacks(ActivityLifecycleLogger())
        Paper.init(this)
        themeManager.setDefaultTheme()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.i("Configuration changed: $newConfig")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.i("The system is running low on memory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Timber.i("Received onTrimMemory event. Level: ${getTrimMemoryLevelName(level)}")
    }

    /**
     * Plants [Timber.Tree] for logging on debug build and reporting crashes on release builds.
     */
    private fun plantTimberTree() {
        Timber.plant(
            if (BuildConfig.DEBUG) {
                DebugTimberTree()
            } else {
                ReleaseTimberTree(this)
            }
        )
    }

    /**
     * Enables penalty logging of all [StrictMode] violations in debug builds.
     */
    private fun configureStrictModePenaltyLogging() {
        if (BuildConfig.DEBUG) {
            enableThreadPolicyPenaltyLogging()
            enableVmPolicyPenaltyLogging()
        }
    }

    /**
     * Enables penalty logging of all [StrictMode.ThreadPolicy] violations.
     */
    private fun enableThreadPolicyPenaltyLogging() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    /**
     * Enables penalty logging of all [StrictMode.VmPolicy] violations.
     */
    private fun enableVmPolicyPenaltyLogging() {
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    /**
     * Converts trim memory level value to a readable format.
     *
     * @param level trim memory level obtained from [onTrimMemory] callback.
     * @return level name string, e.g. "RUNNING_LOW"
     */
    private fun getTrimMemoryLevelName(level: Int): String {
        return when (level) {
            TRIM_MEMORY_COMPLETE -> "COMPLETE"
            TRIM_MEMORY_MODERATE -> "MODERATE"
            TRIM_MEMORY_BACKGROUND -> "BACKGROUND"
            TRIM_MEMORY_UI_HIDDEN -> "UI_HIDDEN"
            TRIM_MEMORY_RUNNING_CRITICAL -> "RUNNING_CRITICAL"
            TRIM_MEMORY_RUNNING_LOW -> "RUNNING_LOW"
            TRIM_MEMORY_RUNNING_MODERATE -> "RUNNING_MODERATE"
            else -> level.toString()
        }
    }
}