//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[CardRippleEffect](index.md)

# CardRippleEffect

class [CardRippleEffect](index.md)(val card: MaterialCardView, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : Effect

Draws a card ripple effect.

An offset of the ripple is equal to the doubled target view padding.

## Constructors

| | |
|---|---|
| [CardRippleEffect](-card-ripple-effect.md) | constructor(card: MaterialCardView, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a new card ripple effect with provided color. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [card](card.md) | private val [card](card.md): MaterialCardView<br>Card view of the target. |
| [color](color.md) | private val [color](color.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Color of the ripple. |
| [duration](duration.md) | open override val [duration](duration.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Default ripple effect duration. |
| [interpolator](interpolator.md) | open override val [interpolator](interpolator.md): [DecelerateInterpolator](https://developer.android.com/reference/kotlin/android/view/animation/DecelerateInterpolator.html)<br>Default ripple effect interpolator. |
| [repeatMode](repeat-mode.md) | open override val [repeatMode](repeat-mode.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default ripple effect repeat mode. |

## Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | open override fun [draw](draw.md)(canvas: [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html), point: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), paint: [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html))<br>Draws card ripple effect. |
