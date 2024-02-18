package me.khruslan.tierlistmaker.util.performance

/**
 * Performance trace captures information about the execution of a specific event.
 *
 * A trace is started in the beginning of the tracked event, and finished in the end. Additionally
 * it may contain various attributes and/or metrics.
 */
interface PerformanceTrace {

    /**
     * Starts the trace.
     *
     * This function must be called before setting any attributes or metrics.
     */
    fun start()

    /**
     * Sets a value (converted to String) for the specified attribute.
     *
     * Attributes are useful for filtering traces.
     *
     * @param name Name of the attribute.
     * @param value Value of the attribute.
     */
    fun putAttribute(name: String, value: Any)

    /**
     * Sets the value (converted to Long) of the metric with the given name in this trace to the
     * value provided.
     *
     * Metrics are useful for measuring additional parameters of the event.
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    fun putMetric(name: String, value: Int)

    /**
     * Sets the value of the metric with the given name in this trace to the value provided.
     *
     * Metrics are useful for measuring additional parameters of the event.
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    fun putMetric(name: String, value: Long)

    /**
     * Stops the trace.
     *
     * After this function is called, it's no longer possible to set any attributes or metrics.
     */
    fun stop()
}