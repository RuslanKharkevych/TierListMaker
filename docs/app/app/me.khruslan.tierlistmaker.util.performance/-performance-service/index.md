//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceService](index.md)

# PerformanceService

interface [PerformanceService](index.md)

Service that starts performance traces.

Can be replaced with a no-op implementation in tests.

#### Inheritors

| |
|---|
| [PerformanceServiceImpl](../-performance-service-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [startTrace](start-trace.md) | abstract fun [startTrace](start-trace.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PerformanceTrace](../-performance-trace/index.md)<br>Creates and starts the trace. |
