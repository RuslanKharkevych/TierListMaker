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

@HiltAndroidApp
class TierListMakerApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

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

    private fun enableLogging() {
        enableStrictModePenaltyLogging()
        Timber.plant(Timber.DebugTree())
    }

    private fun enableStrictModePenaltyLogging() {
        enableThreadPolicyPenaltyLogging()
        enableVmPolicyPenaltyLogging()
    }

    private fun enableThreadPolicyPenaltyLogging() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    private fun enableVmPolicyPenaltyLogging() {
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}