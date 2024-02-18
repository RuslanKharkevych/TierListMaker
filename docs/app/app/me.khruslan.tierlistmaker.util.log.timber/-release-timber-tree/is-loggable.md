//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.timber](../index.md)/[ReleaseTimberTree](index.md)/[isLoggable](is-loggable.md)

# isLoggable

protected open override fun [isLoggable](is-loggable.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Returns whether a message with given priority should be logged.

All messages with [Log.INFO](https://developer.android.com/reference/kotlin/android/util/Log.html#info) level and higher are logged.

#### Return

Whether a message should be logged.

#### Parameters

| | |
|---|---|
| tag | Tag of the log. Ignored. |
| priority | Priority of the log. |
