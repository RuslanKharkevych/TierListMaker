//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceTraceImpl](index.md)/[putMetric](put-metric.md)

# putMetric

open override fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Sets the value (converted to Long) of the metric with the given name in this trace to the value provided.

Calls Trace.putMetric on the wrapped [trace](trace.md) with [value](put-metric.md) converted to [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html).

#### Parameters

| | |
|---|---|
| name | Name of the metric to set. |
| value | The value to which the metric should be set to. |

open override fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))

Sets the value of the metric with the given name in this trace to the value provided.

Calls Trace.putMetric on the wrapped [trace](trace.md).

#### Parameters

| | |
|---|---|
| name | Name of the metric to set. |
| value | The value to which the metric should be set to. |
