//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../../index.md)/[HintGroup](../index.md)/[SpotlightListener](index.md)

# SpotlightListener

private class [SpotlightListener](index.md)(val hintGroupName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : OnSpotlightListener

Listener that logs spotlight started / ended events.

Note that [onStarted](on-started.md) is manually triggered as soon as the listener object is created. This is necessary because OnSpotlightListener.onStarted is not called when the spotlight is started with Spotlight.show function. For correct logging make sure to always call Spotlight.show immediately after the listener is set. Do not use Spotlight.start when this listener is set to avoid duplicated logs.

## Constructors

| | |
|---|---|
| [SpotlightListener](-spotlight-listener.md) | constructor(hintGroupName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates a new spotlight listener with the given hint group name. |

## Properties

| Name | Summary |
|---|---|
| [hintGroupName](hint-group-name.md) | private val [hintGroupName](hint-group-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the hint group. |

## Functions

| Name | Summary |
|---|---|
| [onEnded](on-ended.md) | open override fun [onEnded](on-ended.md)()<br>Logs hint group ended event. |
| [onStarted](on-started.md) | open override fun [onStarted](on-started.md)()<br>Logs hint group started event. |
