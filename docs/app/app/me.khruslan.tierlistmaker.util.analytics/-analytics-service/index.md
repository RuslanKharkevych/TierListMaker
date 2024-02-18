//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[AnalyticsService](index.md)

# AnalyticsService

interface [AnalyticsService](index.md)

Service that logs analytics events.

Can be replaced with no-op implementation in tests.

#### Inheritors

| |
|---|
| [AnalyticsServiceImpl](../-analytics-service-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [logEvent](log-event.md) | abstract fun [logEvent](log-event.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null)<br>Logs analytics event. |
