package me.khruslan.tierlistmaker.utils.view

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import kotlin.math.hypot

/**
 * Set of utilities to simplify creation of complex [Animator] objects.
 */
object AnimatorUtils {

    /**
     * Animates a clipping circle to hide a view.
     *
     * @param view the [View] that will be clipped to the animating circle.
     * @return created [Animator].
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
}