package me.khruslan.tierlistmaker.presentation.utils

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.doOnEnd
import kotlin.math.hypot

/**
 * Set of utilities to simplify creation of complex [Animator] objects.
 *
 * Built on top of [ViewAnimationUtils].
 */
object AnimatorUtils {

    /**
     * Animates a clipping circle to hide a view.
     *
     * This animation has an opposite effect to [ViewAnimationUtils.createCircularReveal].
     *
     * @param view The view that will be clipped to the animating circle.
     * @return Created animator.
     */
    fun createCircularConceal(view: View): Animator {
        val centerX = view.width / 2
        val centerY = view.height / 2
        val startRadius = hypot(centerX.toFloat(), centerY.toFloat())

        return ViewAnimationUtils.createCircularReveal(
            view,
            centerX,
            centerY,
            startRadius,
            0f
        )
    }

    /**
     * Adds an action that will be invoked when the animation has ended.
     *
     * Same as [doOnEnd] but returns [Animator] instead of [Animator.AnimatorListener] to allow
     * using it in a builder.
     *
     * @receiver Animator instance to listen to.
     * @param action Action that should be invoked.
     * @return Same animator instance that was used as extension receiver.
     */
    inline fun Animator.addOnEndAction(crossinline action: (Animator) -> Unit): Animator {
        doOnEnd(action)
        return this
    }
}