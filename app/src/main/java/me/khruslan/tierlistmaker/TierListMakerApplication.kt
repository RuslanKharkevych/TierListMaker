package me.khruslan.tierlistmaker

import android.app.Application
import android.os.StrictMode
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
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
class TierListMakerApplication : Application(), Configuration.Provider {

    /**
     * Factory used to enable dependency injection into workers.
     */
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    /**
     * Manager used to set default theme.
     */
    @Inject
    lateinit var themeManager: ThemeManager

    /**
     * Returns minimum logging lever of work manager:
     * - [Log.VERBOSE] for debug builds;
     * - [Log.ERROR] for release builds.
     */
    private val workManagerLogLevel
        get() = if (BuildConfig.DEBUG) Log.VERBOSE else Log.ERROR

    override fun onCreate() {
        super.onCreate()

        configureStrictModePenaltyLogging()
        plantTimberTree()
        Timber.i("Application created")
        registerActivityLifecycleCallbacks(ActivityLifecycleLogger())
        Paper.init(this)
        themeManager.setDefaultTheme()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Timber.i("The system is running low on memory: $level")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.i("The system is running low on memory")
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(workManagerLogLevel)
            .setWorkerFactory(workerFactory)
            .build()

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
}