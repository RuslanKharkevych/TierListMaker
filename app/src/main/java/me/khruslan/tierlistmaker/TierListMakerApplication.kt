package me.khruslan.tierlistmaker

import android.app.Application
import android.os.StrictMode
import androidx.viewbinding.BuildConfig
import timber.log.Timber

@Suppress("unused")
class TierListMakerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) enableLogging()
    }

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