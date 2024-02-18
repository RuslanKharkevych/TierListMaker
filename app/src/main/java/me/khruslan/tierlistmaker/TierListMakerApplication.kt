package me.khruslan.tierlistmaker

import android.app.Application
import android.content.res.Configuration
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper
import me.khruslan.tierlistmaker.util.log.navigation.ActivityLifecycleLogger
import me.khruslan.tierlistmaker.util.log.timber.DebugTimberTree
import me.khruslan.tierlistmaker.util.log.timber.ReleaseTimberTree
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import timber.log.Timber
import javax.inject.Inject

/**
 * Customized application implementation for startup configurations.
 *
 * Custom application class is also required by [Hilt](https://dagger.dev/hilt) for generating
 * [Dagger](https://dagger.dev) components.
 *
 * @constructor Default constructor called by Android system.
 */
@HiltAndroidApp
class TierListMakerApplication : Application() {

    /**
     * Manager used to set the application theme.
     *
     * Needs to be injected inside the application because updating the theme leads to configuration
     * change. Therefore, it must be done before any activity is created to avoid UI glitches.
     */
    @Inject
    lateinit var themeManager: ThemeManager

    /**
     * Performs global initialization tasks.
     *
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     */
    override fun onCreate() {
        super.onCreate()

        performInitializationTasks()
        Timber.i("Application created")
    }

    /**
     * Logs configuration changes.
     *
     * Called by the system when the device configuration changes while your component is running.
     *
     * @param newConfig The new device configuration.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.i("Configuration changed: $newConfig")
    }

    /**
     * Logs low memory events.
     *
     * This is called when the overall system is running low on memory, and actively running
     * processes should trim their memory usage
     */
    override fun onLowMemory() {
        super.onLowMemory()
        Timber.i("The system is running low on memory")
    }

    /**
     * Logs trim memory events.
     *
     * Called when the operating system has determined that it is a good time for a process to trim
     * unneeded memory from its process.
     *
     * @param level The context of the trim, giving a hint of the amount of trimming the application
     * may like to perform.
     */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Timber.i("Received onTrimMemory event. Level: ${getTrimMemoryLevelName(level)}")
    }

    /**
     * Initializes global components and applies the application theme.
     *
     * Global components include logging services and [Paper](https://github.com/pilgr/Paper)
     * database.
     */
    private fun performInitializationTasks() {
        setupLogging()
        Paper.init(this)
        themeManager.setDefaultTheme()
    }

    /**
     * Initializes logging services.
     *
     * - Configures [StrictMode] penalty logging.
     * - Plants [Timber.Tree].
     * - Registers [ActivityLifecycleLogger].
     */
    private fun setupLogging() {
        configureStrictModePenaltyLogging()
        plantTimberTree()
        registerActivityLifecycleCallbacks(ActivityLifecycleLogger())
    }

    /**
     * Plants [Timber.Tree] for logging in debug build and reporting crashes in release builds.
     *
     * This function must be called as soon as possible to ensure crashes are reported during the
     * application components initialization phase.
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
     *
     * Does nothing in release builds.
     */
    private fun configureStrictModePenaltyLogging() {
        if (BuildConfig.DEBUG) {
            enableThreadPolicyPenaltyLogging()
            enableVmPolicyPenaltyLogging()
        }
    }

    /**
     * Enables penalty logging of all [StrictMode.ThreadPolicy] violations.
     *
     * To catch violations during initialization phase, this function should be called immediately
     * when the application is created.
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
     *
     * To catch violations during initialization phase, this function should be called immediately
     * when the application is created.
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
     * If level is unknown, simply converts it to string as is.
     *
     * @param level Trim memory level obtained from [onTrimMemory] callback.
     * @return Level name string, e.g. "RUNNING_LOW".
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