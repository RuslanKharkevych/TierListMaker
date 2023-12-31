package me.khruslan.tierlistmaker.presentation.utils.hints.core.effects

import androidx.annotation.ColorInt
import com.takusemba.spotlight.effet.Effect
import com.takusemba.spotlight.effet.RippleEffect

/**
 * Draws a circular ripple effect.
 *
 * @param delegate ripple effect delegate.
 */
class CircularRippleEffect private constructor(delegate: RippleEffect) : Effect by delegate {

    /**
     * @constructor Creates a circular ripple effect for the target view with provided radius and
     * color. An offset of the ripple doubles the radius of the target.
     *
     * @param radius radius of the target view.
     * @param color color of the ripple.
     */
    constructor(radius: Float, @ColorInt color: Int) : this(
        RippleEffect(
            offset = radius,
            radius = radius * 2,
            color = color
        )
    )
}