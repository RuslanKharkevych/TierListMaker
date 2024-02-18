package me.khruslan.tierlistmaker.presentation.utils.hints.core

import android.app.Activity
import android.graphics.Color
import androidx.core.graphics.ColorUtils
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import timber.log.Timber

/**
 * An ordered group of hints.
 *
 * Implemented with [Spotlight]. User can navigate back and forwards through hints.
 *
 * @param T Type of the hint step.
 * @property name The name of the hint group.
 * @property activity Hosting activity.
 * @property steps The list of hint steps.
 * @constructor Creates a new hint group.
 */
abstract class HintGroup<T: Indexable>(
    private val name: String,
    private val activity: Activity,
    private val steps: List<T>
) {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * The opacity of the [Spotlight] background in [0..255] range.
         */
        private const val SCRIM_ALPHA = 220
    }

    /**
     * Provides a factory for creating hints.
     *
     * @return Provided factory.
     */
    protected abstract fun hintFactory(): HintFactory<T>

    /**
     * Shows the hint group starting with the given step.
     *
     * This function creates hints, registers hint listeners, builds and shows [Spotlight].
     *
     * @param step Step to be shown.
     */
    open fun show(step: T) {
        val hints = createHints()
        val spotlight = buildSpotlight(hints)
        setHintListeners(hints, spotlight)
        spotlight.show(step.index)
    }

    /**
     * Creates a hint for each step using [HintFactory].
     *
     * @return The list of created hints.
     */
    private fun createHints(): List<Hint> {
        val factory = hintFactory()
        return steps.map { step ->
            factory.create(step)
        }
    }

    /**
     * Builds a spotlight instance with the provided hints.
     *
     * Additionally registers [SpotlightListener] for logging spotlight events.
     *
     * @param hints Hints that will be set as targets for this spotlight.
     * @return Spotlight instance that is ready to be shown.
     */
    private fun buildSpotlight(hints: List<Hint>): Spotlight {
        return Spotlight.Builder(activity)
            .setTargets(mapTargets(hints))
            .setBackgroundColor(ColorUtils.setAlphaComponent(Color.BLACK, SCRIM_ALPHA))
            .setOnSpotlightListener(SpotlightListener(name))
            .build()
    }

    /**
     * Maps hints to targets for use by [Spotlight].
     *
     * @param hints The list of hints.
     * @return Mapped targets.
     */
    private fun mapTargets(hints: List<Hint>): List<Target> {
        return hints.map { it.target }
    }

    /**
     * Registers [HintListener] for each hint.
     *
     * Note that the same listener is used for all hints.
     *
     * @param hints The list of hints.
     * @param spotlight The spotlight instance.
     */
    private fun setHintListeners(hints: List<Hint>, spotlight: Spotlight) {
        val listener = HintListener(spotlight)
        hints.forEach { hint ->
            hint.setOnHintListener(listener)
        }
    }

    /**
     * Listener that logs spotlight started / ended events.
     *
     * Note that [onStarted] is manually triggered as soon as the listener object is created. This
     * is necessary because [OnSpotlightListener.onStarted] is not called when the spotlight is
     * started with [Spotlight.show] function. For correct logging make sure to always call
     * [Spotlight.show] immediately after the listener is set. Do not use [Spotlight.start] when
     * this listener is set to avoid duplicated logs.
     *
     * @property hintGroupName The name of the hint group.
     * @constructor Creates a new spotlight listener with the given hint group name.
     */
    private class SpotlightListener(private val hintGroupName: String) : OnSpotlightListener {

        init {
            onStarted()
        }

        /**
         * Logs hint group started event.
         *
         * Manually called during class initialization.
         */
        override fun onStarted() {
            Timber.i("$hintGroupName started")
        }

        /**
         * Logs hint group ended event.
         *
         * Called when spotlight is closed.
         */
        override fun onEnded() {
            Timber.i("$hintGroupName ended")
        }
    }

    /**
     * Listener that uses spotlight instance to handle hint events.
     *
     * Must be set for all hints in the hint group. For better performance it's possible to reuse
     * the same listener instance.
     *
     * @property spotlight The spotlight that hosts the hint.
     * @constructor Creates a new hint listener with the given spotlight.
     */
    private class HintListener(private val spotlight: Spotlight) : OnHintListener {

        /**
         * Calls [Spotlight.previous].
         *
         * Invoked when user navigates to the previous hint.
         */
        override fun onPrevious() {
            spotlight.previous()
        }

        /**
         * Calls [Spotlight.next].
         *
         * Invoked when user navigates to the next hint.
         */
        override fun onNext() {
            spotlight.next()
        }

        /**
         * Calls [Spotlight.finish].
         *
         * Invoked when user closes the hint group.
         */
        override fun onCloseGroup() {
            spotlight.finish()
        }
    }
}