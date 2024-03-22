package me.khruslan.tierlistmaker.presentation.utils.hints.core.effects

import android.graphics.Paint
import com.takusemba.spotlight.effet.Effect
import com.takusemba.spotlight.effet.RippleEffect

/**
 * A base class for rectangular ripple effects.
 *
 * An adaptation of [RippleEffect] compliant with rectangular shape. Used instead of direct
 * inheritance because [RippleEffect] is a final class.
 */
abstract class BaseRectangularRippleEffect : Effect {

    /**
     * Shared constants.
     */
    protected companion object Constants {

        /**
         * Maximum alpha value (fully opaque) in [0..255] range.
         *
         * This format for alpha is used in some places in Android SDK, such as [Paint.setAlpha].
         */
        @JvmStatic
        // https://youtrack.jetbrains.com/issue/KT-39868
        @Suppress("JVM_STATIC_ON_CONST_OR_JVM_FIELD")
        protected const val MAX_ALPHA = 255f
    }

    /**
     * Default ripple effect duration.
     */
    override val duration = RippleEffect.DEFAULT_DURATION

    /**
     * Default ripple effect interpolator.
     */
    override val interpolator = RippleEffect.DEFAULT_INTERPOLATOR

    /**
     * Default ripple effect repeat mode.
     */
    override val repeatMode = RippleEffect.DEFAULT_REPEAT_MODE
}