package me.khruslan.tierlistmaker.presentation.utils.hints.core.effects

import androidx.annotation.ColorInt
import com.takusemba.spotlight.effet.Effect
import com.takusemba.spotlight.effet.RippleEffect

/**
 * Draws a circular ripple effect.
 *
 * It's a helper for creating [RippleEffect] without explicitly specifying radius.
 *
 * @param delegate Ripple effect delegate.
 * @constructor Creates a circular ripple effect from the delegate.
 */
class CircularRippleEffect private constructor(delegate: RippleEffect) : Effect by delegate {

    /**
     * Creates a circular ripple effect for the target view with provided radius and color. An
     * offset of the ripple doubles the radius of the target.
     *
     * @param radius Radius of the target view.
     * @param color Color of the ripple.
     */
    constructor(radius: Float, @ColorInt color: Int) : this(
        RippleEffect(
            offset = radius,
            radius = radius * 2,
            color = color
        )
    )
}