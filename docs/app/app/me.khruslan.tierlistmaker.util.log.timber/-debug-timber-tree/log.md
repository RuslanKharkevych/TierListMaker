//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.timber](../index.md)/[DebugTimberTree](index.md)/[log](log.md)

# log

protected open override fun [log](log.md)(priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)

Writes a log message to its destination.

Overrides [tag](log.md) with [LOG_TAG](-constants/-l-o-g_-t-a-g.md). Called for all level-specific methods by default.

#### Parameters

| | |
|---|---|
| priority | Log level. See [Log](https://developer.android.com/reference/kotlin/android/util/Log.html) for constants. |
| tag | Explicit or inferred tag. Always ignored. |
| message | Formatted log message. |
| t | Accompanying exception. May be null. |
