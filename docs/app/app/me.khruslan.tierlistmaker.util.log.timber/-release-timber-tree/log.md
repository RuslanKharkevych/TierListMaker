//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.timber](../index.md)/[ReleaseTimberTree](index.md)/[log](log.md)

# log

protected open override fun [log](log.md)(priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)

Write a log message to its destination.

If exception is not null, records a report to send to Crashlytics. Otherwise logs a message that will be included in the next report. Called for all level-specific methods by default.

#### Parameters

| | |
|---|---|
| priority | Log level. Always ignored. |
| tag | Explicit or inferred tag. Always ignored. |
| message | Formatted log message. |
| t | Accompanying exception. May be null. |
