package me.khruslan.tierlistmaker.util.performance

import com.google.firebase.perf.metrics.Trace

/**
 * [PerformanceTrace] implementation.
 *
 * Implemented with Firebase Performance SDK.
 *
 * @param name Name of the trace.
 * @constructor Creates performance trace with a given name.
 */
class PerformanceTraceImpl(name: String) : PerformanceTrace {

    /**
     * Wrapped Firebase Performance trace.
     */
    private val trace = Trace.create(name)

    /**
     * Starts the trace.
     *
     * Calls [Trace.start] on the wrapped [trace].
     */
    override fun start() {
        trace.start()
    }

    /**
     * Sets a value (converted to String) for the specified attribute.
     *
     * Calls [Trace.putAttribute] on the wrapped [trace] with [value] converted to [String].
     *
     * @param name Name of the attribute.
     * @param value Value of the attribute.
     */
    override fun putAttribute(name: String, value: Any) {
        trace.putAttribute(name, value.toString())
    }

    /**
     * Sets the value (converted to Long) of the metric with the given name in this trace to the
     * value provided.
     *
     * Calls [Trace.putMetric] on the wrapped [trace] with [value] converted to [Long].
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    override fun putMetric(name: String, value: Int) {
        putMetric(name, value.toLong())
    }

    /**
     * Sets the value of the metric with the given name in this trace to the value provided.
     *
     * Calls [Trace.putMetric] on the wrapped [trace].
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    override fun putMetric(name: String, value: Long) {
        trace.putMetric(name, value)
    }

    /**
     * Stops the trace.
     *
     * Calls [Trace.stop] on the wrapped trace.
     */
    override fun stop() {
        trace.stop()
    }
}