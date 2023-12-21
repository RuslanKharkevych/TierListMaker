package me.khruslan.tierlistmaker.util.performace

import javax.inject.Inject

/**
 * [PerformanceService] implementation that uses [PerformanceTraceImpl].
 */
class PerformanceServiceImpl @Inject constructor(): PerformanceService {

    override fun startTrace(name: String): PerformanceTrace {
        return PerformanceTraceImpl(name).also { it.start() }
    }
}