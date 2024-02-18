package me.khruslan.tierlistmaker.util.performance

import javax.inject.Inject

/**
 * [PerformanceService] implementation.
 *
 * As a trace implementation [PerformanceTraceImpl] is used.
 *
 * @constructor Creates a new performance service instance.
 */
class PerformanceServiceImpl @Inject constructor(): PerformanceService {

    /**
     * Creates and starts the trace.
     *
     * To stop the trace, call [PerformanceTrace.stop] on a returned object.
     *
     * @param name Name of the trace.
     * @return Created trace object.
     */
    override fun startTrace(name: String): PerformanceTrace {
        return PerformanceTraceImpl(name).also { it.start() }
    }
}