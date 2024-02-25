//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceTrace](index.md)/[putMetric](put-metric.md)

# putMetric

abstract fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Sets the value (converted to Long) of the metric with the given name in this trace to the value provided.

Metrics are useful for measuring additional parameters of the event.

#### Parameters

| | |
|---|---|
| name | Name of the metric to set. |
| value | The value to which the metric should be set to. |
<br>
---
<br>

abstract fun [putMetric](put-metric.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))

Sets the value of the metric with the given name in this trace to the value provided.

Metrics are useful for measuring additional parameters of the event.

#### Parameters

| | |
|---|---|
| name | Name of the metric to set. |
| value | The value to which the metric should be set to. |
