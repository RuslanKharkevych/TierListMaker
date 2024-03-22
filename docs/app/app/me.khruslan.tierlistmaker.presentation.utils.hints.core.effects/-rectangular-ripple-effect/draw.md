//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[RectangularRippleEffect](index.md)/[draw](draw.md)

# draw

open override fun [draw](draw.md)(canvas: [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html), point: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), paint: [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html))

Draws a square ripple effect.

The color opacity is animated from 0 to 1. The ripple offset is animated from 0 to [offset](offset.md).

#### Parameters

| | |
|---|---|
| canvas | Canvas to draw on. |
| point | Coordinate in the center of the anchor. |
| value | The animated value from 0 to 1, which is looped until target finishes. |
| paint | Paint for customizing ripple style and color. |
