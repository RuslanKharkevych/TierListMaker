//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceTrace](index.md)

# PerformanceTrace

interface [PerformanceTrace](index.md)

Performance trace captures information about the execution of a specific event.

A trace is started in the beginning of the tracked event, and finished in the end. Additionally it may contain various attributes and/or metrics.

#### Inheritors

| |
|---|
| [PerformanceTraceImpl](../-performance-trace-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [putAttribute](put-attribute.md) | abstract fun [putAttribute](put-attribute.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))<br>Sets a value (converted to String) for the specified attribute. |
| [putMetric](put-metric.md) | abstract fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Sets the value (converted to Long) of the metric with the given name in this trace to the value provided.<br>abstract fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))<br>Sets the value of the metric with the given name in this trace to the value provided. |
| [start](start.md) | abstract fun [start](start.md)()<br>Starts the trace. |
| [stop](stop.md) | abstract fun [stop](stop.md)()<br>Stops the trace. |
