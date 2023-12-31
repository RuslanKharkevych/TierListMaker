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
 * @param T type of the hint step.
 * @property name the name of the hint group.
 * @property activity hosting activity.
 * @property steps the list of hint steps.
 */
abstract class HintGroup<T: Indexable>(
    private val name: String,
    private val activity: Activity,
    private val steps: List<T>
) {

    private companion object {
        private const val SCRIM_ALPHA = 220
    }

    /**
     * Provides a factory for creating hints.
     *
     * @return provided factory.
     */
    protected abstract fun hintFactory(): HintFactory<T>

    /**
     * Shows the hint group starting with the given step.
     *
     * @param step step to be shown.
     */
    open fun show(step: T) {
        val hints = createHints()
        val spotlight = buildSpotlight(hints)
        setHintListeners(hints, spotlight)
        spotlight.show(step.index)
    }

    /**
     * Create a hint for each step using [HintFactory].
     *
     * @return the list of created hints.
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
     * @param hints hints that will be set as targets for this spotlight.
     * @return spotlight instance that is ready to be shown.
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
     * @param hints the list of hints.
     * @return mapped targets.
     */
    private fun mapTargets(hints: List<Hint>): List<Target> {
        return hints.map { it.target }
    }

    /**
     * Sets [HintListener] for each hint.
     *
     * @param hints the list of hints.
     * @param spotlight the spotlight instance.
     */
    private fun setHintListeners(hints: List<Hint>, spotlight: Spotlight) {
        val listener = HintListener(spotlight)
        hints.forEach { hint ->
            hint.setOnHintListener(listener)
        }
    }

    /**
     * Listener that logs spotlight started / ended events. Note that [onStarted] is manually
     * triggered as soon as the listener object is created. This is necessary because
     * [OnSpotlightListener.onStarted] is not called when the spotlight is started with
     * [Spotlight.show] function. For correct logging make sure to always call [Spotlight.show]
     * immediately after the listener is set. Do not use [Spotlight.start] when this listener is
     * set to avoid duplicated logs.
     *
     * @property hintGroupName the name of the hint group.
     */
    private class SpotlightListener(private val hintGroupName: String) : OnSpotlightListener {

        init {
            onStarted()
        }

        override fun onStarted() {
            Timber.i("$hintGroupName started")
        }

        override fun onEnded() {
            Timber.i("$hintGroupName ended")
        }
    }

    /**
     * Listener that uses spotlight instance to handle hint events.
     *
     * @property spotlight the spotlight that hosts the hint.
     */
    private class HintListener(private val spotlight: Spotlight) : OnHintListener {

        override fun onPrevious() {
            spotlight.previous()
        }

        override fun onNext() {
            spotlight.next()
        }

        override fun onCloseGroup() {
            spotlight.finish()
        }
    }
}