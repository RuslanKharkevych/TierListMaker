//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../../index.md)/[Hint](../index.md)/[TargetListener](index.md)

# TargetListener

private class [TargetListener](index.md)(val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : OnTargetListener

Listener that logs hint started / ended events.

## Constructors

| | |
|---|---|
| [TargetListener](-target-listener.md) | constructor(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates a new listener for a given hint name. |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | private val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of the hint. |

## Functions

| Name | Summary |
|---|---|
| [onEnded](on-ended.md) | open override fun [onEnded](on-ended.md)()<br>Logs hint ended event. |
| [onStarted](on-started.md) | open override fun [onStarted](on-started.md)()<br>Logs hint started event. |
