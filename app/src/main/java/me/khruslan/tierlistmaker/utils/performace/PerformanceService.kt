package me.khruslan.tierlistmaker.utils.performace

/**
 * Service that starts performance traces.
 */
interface PerformanceService {

    /**
     * Creates and starts the trace.
     *
     * @param name Name of the trace.
     * @return Created trace object.
     */
    fun startTrace(name: String): PerformanceTrace
}