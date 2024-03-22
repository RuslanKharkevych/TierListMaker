//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core.effects](../index.md)/[BaseRectangularRippleEffect](index.md)

# BaseRectangularRippleEffect

abstract class [BaseRectangularRippleEffect](index.md) : Effect

A base class for rectangular ripple effects.

An adaptation of RippleEffect compliant with rectangular shape. Used instead of direct inheritance because RippleEffect is a final class.

#### Inheritors

| |
|---|
| [CardRippleEffect](../-card-ripple-effect/index.md) |
| [RectangularRippleEffect](../-rectangular-ripple-effect/index.md) |

## Constructors

| | |
|---|---|
| [BaseRectangularRippleEffect](-base-rectangular-ripple-effect.md) | constructor() |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | protected object [Constants](-constants/index.md)<br>Shared constants. |

## Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | open override val [duration](duration.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Default ripple effect duration. |
| [interpolator](interpolator.md) | open override val [interpolator](interpolator.md): [DecelerateInterpolator](https://developer.android.com/reference/kotlin/android/view/animation/DecelerateInterpolator.html)<br>Default ripple effect interpolator. |
| [repeatMode](repeat-mode.md) | open override val [repeatMode](repeat-mode.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default ripple effect repeat mode. |
