//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[AnalyticsServiceImpl](index.md)

# AnalyticsServiceImpl

class [AnalyticsServiceImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor : [AnalyticsService](../-analytics-service/index.md)

[AnalyticsService](../-analytics-service/index.md) implementation.

Logs analytics events to Firebase.

## Constructors

| | |
|---|---|
| [AnalyticsServiceImpl](-analytics-service-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor()<br>Creates a new analytics service. |

## Functions

| Name | Summary |
|---|---|
| [logEvent](log-event.md) | open override fun [logEvent](log-event.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?)<br>Logs analytics event. |
