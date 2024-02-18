//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[CircularRippleEffect](index.md)

# CircularRippleEffect

class [CircularRippleEffect](index.md)(delegate: RippleEffect) : Effect

Draws a circular ripple effect.

It's a helper for creating RippleEffect without explicitly specifying radius.

#### Parameters

| | |
|---|---|
| delegate | Ripple effect delegate. |

## Constructors

| | |
|---|---|
| [CircularRippleEffect](-circular-ripple-effect.md) | constructor(radius: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a circular ripple effect for the target view with provided radius and color. An offset of the ripple doubles the radius of the target.<br>private constructor(delegate: RippleEffect)<br>Creates a circular ripple effect from the delegate. |
