//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../../index.md)/[HintGroup](../index.md)/[HintListener](index.md)

# HintListener

private class [HintListener](index.md)(val spotlight: Spotlight) : [OnHintListener](../../-on-hint-listener/index.md)

Listener that uses spotlight instance to handle hint events.

Must be set for all hints in the hint group. For better performance it's possible to reuse the same listener instance.

## Constructors

| | |
|---|---|
| [HintListener](-hint-listener.md) | constructor(spotlight: Spotlight)<br>Creates a new hint listener with the given spotlight. |

## Properties

| Name | Summary |
|---|---|
| [spotlight](spotlight.md) | private val [spotlight](spotlight.md): Spotlight<br>The spotlight that hosts the hint. |

## Functions

| Name | Summary |
|---|---|
| [onCloseGroup](on-close-group.md) | open override fun [onCloseGroup](on-close-group.md)()<br>Calls Spotlight.finish. |
| [onNext](on-next.md) | open override fun [onNext](on-next.md)()<br>Calls Spotlight.next. |
| [onPrevious](on-previous.md) | open override fun [onPrevious](on-previous.md)()<br>Calls Spotlight.previous. |
