package me.khruslan.tierlistmaker.utils.extensions

import android.animation.Animator
import androidx.core.animation.doOnEnd

/**
 * Adds an action that will be invoked when the animation has ended. Same as [doOnEnd] but returns
 * [Animator] instead of [Animator.AnimatorListener] to allow using it in a builder.
 *
 * @param action action that should be invoked.
 * @return same [Animator] instance that was used as extension receiver.
 */
inline fun Animator.addOnEndAction(crossinline action: (Animator) -> Unit): Animator {
    doOnEnd(action)
    return this
}