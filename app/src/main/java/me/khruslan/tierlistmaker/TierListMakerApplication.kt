package me.khruslan.tierlistmaker

import android.app.Application
import android.os.StrictMode
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper
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
     * Returns minimum logging lever of work manager:
     * - [Log.VERBOSE] for debug builds;
     * - [Log.ERROR] for release builds;
     */
    private val workManagerLogLevel
        get() = if (BuildConfig.DEBUG) Log.VERBOSE else Log.ERROR

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) enableLogging()
        Paper.init(this)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(workManagerLogLevel)
            .setWorkerFactory(workerFactory)
            .build()

    /**
     * Enables logging:
     * - Enables penalty logging of all [StrictMode] violations;
     * - Plants [Timber.DebugTree].
     */
    private fun enableLogging() {
        enableStrictModePenaltyLogging()
        Timber.plant(Timber.DebugTree())
    }

    /**
     * Enables penalty logging of all [StrictMode] violations.
     */
    private fun enableStrictModePenaltyLogging() {
        enableThreadPolicyPenaltyLogging()
        enableVmPolicyPenaltyLogging()
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