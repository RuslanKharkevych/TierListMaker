//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollManager](index.md)/[getScrollOffset](get-scroll-offset.md)

# getScrollOffset

private fun [getScrollOffset](get-scroll-offset.md)(direction: [AutoScrollDirection](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-direction/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Returns scroll offset for the given direction.

For [AutoScrollDirection.Up](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-direction/-up/index.md) scroll offset will be negative. For [AutoScrollDirection.Down](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-direction/-down/index.md) - positive.

#### Return

Scroll offset in pixels.

#### Parameters

| | |
|---|---|
| direction | Scroll direction. |
