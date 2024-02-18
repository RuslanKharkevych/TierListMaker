//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes](../index.md)/[EmptyShape](index.md)

# EmptyShape

class [EmptyShape](index.md) : Shape

No-op shape.

Can be used if target doesn't exist.

## Constructors

| | |
|---|---|
| [EmptyShape](-empty-shape.md) | constructor()<br>Creates an empty shape. |

## Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | open override val [duration](duration.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0<br>Zero duration. |
| [interpolator](interpolator.md) | open override val [interpolator](interpolator.md): [LinearInterpolator](https://developer.android.com/reference/kotlin/android/view/animation/LinearInterpolator.html)<br>Linear interpolator. |

## Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | open override fun [draw](draw.md)(canvas: [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html), point: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), paint: [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html))<br>Draws nothing. |
