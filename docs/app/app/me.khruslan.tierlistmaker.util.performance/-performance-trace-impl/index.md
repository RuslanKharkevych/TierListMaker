//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceTraceImpl](index.md)

# PerformanceTraceImpl

class [PerformanceTraceImpl](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [PerformanceTrace](../-performance-trace/index.md)

[PerformanceTrace](../-performance-trace/index.md) implementation.

Implemented with Firebase Performance SDK.

#### Parameters

| | |
|---|---|
| name | Name of the trace. |

## Constructors

| | |
|---|---|
| [PerformanceTraceImpl](-performance-trace-impl.md) | constructor(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates performance trace with a given name. |

## Properties

| Name | Summary |
|---|---|
| [trace](trace.md) | private val [trace](trace.md): Trace<br>Wrapped Firebase Performance trace. |

## Functions

| Name | Summary |
|---|---|
| [putAttribute](put-attribute.md) | open override fun [putAttribute](put-attribute.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))<br>Sets a value (converted to String) for the specified attribute. |
| [putMetric](put-metric.md) | open override fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Sets the value (converted to Long) of the metric with the given name in this trace to the value provided.<br>open override fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))<br>Sets the value of the metric with the given name in this trace to the value provided. |
| [start](start.md) | open override fun [start](start.md)()<br>Starts the trace. |
| [stop](stop.md) | open override fun [stop](stop.md)()<br>Stops the trace. |
