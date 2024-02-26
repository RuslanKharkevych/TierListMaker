//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.performance](../index.md)/[PerformanceServiceImpl](index.md)

# PerformanceServiceImpl

class [PerformanceServiceImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor : [PerformanceService](../-performance-service/index.md)

[PerformanceService](../-performance-service/index.md) implementation.

As a trace implementation [PerformanceTraceImpl](../-performance-trace-impl/index.md) is used.

## Constructors

| | |
|---|---|
| [PerformanceServiceImpl](-performance-service-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor()<br>Creates a new performance service instance. |

## Functions

| Name | Summary |
|---|---|
| [startTrace](start-trace.md) | open override fun [startTrace](start-trace.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PerformanceTrace](../-performance-trace/index.md)<br>Creates and starts the trace. |
