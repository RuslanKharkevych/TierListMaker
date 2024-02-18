package me.khruslan.tierlistmaker.util.performance

/**
 * Service that starts performance traces.
 *
 * Can be replaced with a no-op implementation in tests.
 */
interface PerformanceService {

    /**
     * Creates and starts the trace.
     *
     * To stop the trace, call [PerformanceTrace.stop] on a returned object.
     *
     * @param name Name of the trace.
     * @return Created trace object.
     */
    fun startTrace(name: String): PerformanceTrace
}