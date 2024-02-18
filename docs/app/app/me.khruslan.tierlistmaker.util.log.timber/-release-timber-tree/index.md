//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.timber](../index.md)/[ReleaseTimberTree](index.md)

# ReleaseTimberTree

class [ReleaseTimberTree](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [Timber.Tree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-tree/index.html)

[Timber.Tree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-tree/index.html) implementation for release builds.

Captures non-fatal exceptions and sends them to the Firebase.crashlytics.

#### Parameters

| | |
|---|---|
| context | Application context. |

## Constructors

| | |
|---|---|
| [ReleaseTimberTree](-release-timber-tree.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Creates a new release tree. |

## Functions

| Name | Summary |
|---|---|
| [isLoggable](is-loggable.md) | protected open override fun [isLoggable](is-loggable.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns whether a message with given priority should be logged. |
| [log](log.md) | protected open override fun [log](log.md)(priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)<br>Write a log message to its destination. |
| [setCustomKeys](set-custom-keys.md) | private fun [setCustomKeys](set-custom-keys.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Sets custom Firebase.crashlytics keys. |
