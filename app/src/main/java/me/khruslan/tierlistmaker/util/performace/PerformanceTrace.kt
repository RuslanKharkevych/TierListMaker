package me.khruslan.tierlistmaker.util.performace

/**
 * Performance trace captures information about the execution of a specific event.
 */
interface PerformanceTrace {

    /**
     * Starts the trace.
     */
    fun start()

    /**
     * Sets a value (converted to String) for the specified attribute.
     *
     * @param name Name of the attribute.
     * @param value Value of the attribute.
     */
    fun putAttribute(name: String, value: Any)

    /**
     * Sets the value (converted to Long) of the metric with the given name in this trace to the value
     * provided.
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    fun putMetric(name: String, value: Int)

    /**
     * Sets the value of the metric with the given name in this trace to the value provided.
     *
     * @param name Name of the metric to set.
     * @param value The value to which the metric should be set to.
     */
    fun putMetric(name: String, value: Long)

    /**
     * Stops the trace.
     */
    fun stop()
}