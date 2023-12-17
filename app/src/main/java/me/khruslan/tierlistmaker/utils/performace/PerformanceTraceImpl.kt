package me.khruslan.tierlistmaker.utils.performace

import com.google.firebase.perf.metrics.Trace

/**
 * [PerformanceTrace] implementation that uses Firebase Performance.
 *
 * @param name Name of the trace.
 */
class PerformanceTraceImpl(name: String) : PerformanceTrace {

    /**
     * Wrapped Firebase Performance trace.
     */
    private val trace = Trace.create(name)

    override fun start() {
        trace.start()
    }

    override fun putAttribute(name: String, value: Any) {
        trace.putAttribute(name, value.toString())
    }

    override fun putMetric(name: String, value: Int) {
        putMetric(name, value.toLong())
    }

    override fun putMetric(name: String, value: Long) {
        trace.putMetric(name, value)
    }

    override fun stop() {
        trace.stop()
    }
}