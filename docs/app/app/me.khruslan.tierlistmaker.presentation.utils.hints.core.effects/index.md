//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](index.md)

# Package-level declarations

UI utilities for customizing hint effects.

## Types

| Name | Summary |
|---|---|
| [BaseRectangularRippleEffect](-base-rectangular-ripple-effect/index.md) | abstract class [BaseRectangularRippleEffect](-base-rectangular-ripple-effect/index.md) : Effect<br>A base class for rectangular ripple effects. |
| [CardRippleEffect](-card-ripple-effect/index.md) | class [CardRippleEffect](-card-ripple-effect/index.md)(val card: MaterialCardView, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [BaseRectangularRippleEffect](-base-rectangular-ripple-effect/index.md)<br>Draws a card ripple effect. |
| [CircularRippleEffect](-circular-ripple-effect/index.md) | class [CircularRippleEffect](-circular-ripple-effect/index.md)(delegate: RippleEffect) : Effect<br>Draws a circular ripple effect. |
| [RectangularRippleEffect](-rectangular-ripple-effect/index.md) | class [RectangularRippleEffect](-rectangular-ripple-effect/index.md)(val height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val offset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [BaseRectangularRippleEffect](-base-rectangular-ripple-effect/index.md)<br>Draws a rectangular ripple effect. |
