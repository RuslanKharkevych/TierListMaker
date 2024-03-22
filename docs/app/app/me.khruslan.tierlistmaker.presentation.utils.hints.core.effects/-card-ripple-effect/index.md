//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[CardRippleEffect](index.md)

# CardRippleEffect

class [CardRippleEffect](index.md)(val card: MaterialCardView, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [BaseRectangularRippleEffect](../-base-rectangular-ripple-effect/index.md)

Draws a card ripple effect.

An offset of the ripple is equal to the doubled target view padding.

## Constructors

| | |
|---|---|
| [CardRippleEffect](-card-ripple-effect.md) | constructor(card: MaterialCardView, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a new card ripple effect with provided color. |

## Properties

| Name | Summary |
|---|---|
| [card](card.md) | private val [card](card.md): MaterialCardView<br>Card view of the target. |
| [color](color.md) | private val [color](color.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Color of the ripple. |

## Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | open override fun [draw](draw.md)(canvas: [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html), point: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), paint: [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html))<br>Draws card ripple effect. |
