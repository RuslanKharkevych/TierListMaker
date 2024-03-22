//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[RectangularRippleEffect](index.md)

# RectangularRippleEffect

class [RectangularRippleEffect](index.md)(val height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val offset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [BaseRectangularRippleEffect](../-base-rectangular-ripple-effect/index.md)

Draws a rectangular ripple effect.

The corners of the rectangle are sharp.

## Constructors

| | |
|---|---|
| [RectangularRippleEffect](-rectangular-ripple-effect.md) | constructor(height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), offset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a new square ripple effect with provided attributes. |

## Properties

| Name | Summary |
|---|---|
| [color](color.md) | private val [color](color.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Color of the ripple. |
| [height](height.md) | private val [height](height.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Height of the rectangle in pixels. |
| [offset](offset.md) | private val [offset](offset.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [width](width.md) | private val [width](width.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Width of the rectangle in pixels. |

## Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | open override fun [draw](draw.md)(canvas: [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html), point: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), paint: [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html))<br>Draws a square ripple effect. |
