//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.timber](../index.md)/[DebugTimberTree](index.md)

# DebugTimberTree

class [DebugTimberTree](index.md) : [Timber.DebugTree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-debug-tree/index.html)

[Timber.Tree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-tree/index.html) implementation for debug builds.

Logs messages to the console. Unlike [Timber.DebugTree](https://jakewharton.github.io/timber/docs/5.x/timber/timber.log/-timber/-debug-tree/index.html), uses a single tag for all logs.

## Constructors

| | |
|---|---|
| [DebugTimberTree](-debug-timber-tree.md) | constructor()<br>Creates a new debug tree. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal usage. |

## Functions

| Name | Summary |
|---|---|
| [log](log.md) | protected open override fun [log](log.md)(priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)<br>Writes a log message to its destination. |
