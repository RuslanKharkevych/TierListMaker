package me.khruslan.tierlistmaker.presentation.utils

import android.view.View
import androidx.preference.Preference

/**
 * Implements throttling mechanism to prevent duplicated click events.
 *
 * Unlike [View.OnClickListener], the throttler is not meant to be shared between multiple views.
 *
 * @constructor Default no-arg constructor.
 */
private abstract class ClickThrottler {

    /**
     * The timestamp of the last click.
     *
     * Used to calculate elapsed time for the next click. Note that if click is blocked, this field
     * does not get updated.
     */
    private var lastClickTimestamp = 0L

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * Minimum interval between clicks.
         *
         * If multiple clicks happen during these interval, they will be blocked.
         */
        private const val MIN_CLICK_INTERVAL_MILLIS = 2000L
    }

    /**
     * Invoked when click event is allowed.
     *
     * If a click is duplicated, no events are passed to a client.
     */
    abstract fun onThrottledClick()

    /**
     * Throttles the click event.
     *
     * If the click is allowed, returns true and invokes [onThrottledClick] listener. Otherwise
     * returns false without taking action. The click is blocked if elapsed time from the last click
     * is less than [MIN_CLICK_INTERVAL_MILLIS].
     *
     * @return Whether the click event was allowed.
     */
    protected fun throttleClick(): Boolean {
        val currentClickTimestamp = System.currentTimeMillis()
        val elapsedTime = currentClickTimestamp - lastClickTimestamp
        if (elapsedTime < MIN_CLICK_INTERVAL_MILLIS) return false

        lastClickTimestamp = currentClickTimestamp
        onThrottledClick()
        return true
    }
}

/**
 * Extension of [View.OnClickListener] that prevents duplicate clicks.
 *
 * Clients must override [ClickThrottler.onThrottledClick] instead of [onClick].
 *
 * @constructor Default no-arg constructor.
 */
private abstract class OnThrottledClickListener : ClickThrottler(), View.OnClickListener {

    /**
     * Invokes [ClickThrottler.onThrottledClick] if click is not duplicated.
     *
     * Called when a view was clicked.
     *
     * @param v The view that was clicked.
     */
    final override fun onClick(v: View?) {
        throttleClick()
    }
}

/**
 * Extension of [Preference.OnPreferenceClickListener] that prevents multiple clicks.
 *
 * Clients must override [ClickThrottler.onThrottledClick] instead of [onPreferenceClick].
 *
 * @constructor Default no-arg constructor.
 */
private abstract class OnThrottledPreferenceClickListener : ClickThrottler(),
    Preference.OnPreferenceClickListener {

    /**
     * Invokes [ClickThrottler.onThrottledClick] if click is not duplicated.
     *
     * Called when a preference has been clicked.
     *
     * @param preference The preference that was clicked.
     * @return True if the click was handled.
     */
    final override fun onPreferenceClick(preference: Preference): Boolean {
        return throttleClick()
    }
}

/**
 * Sets [OnThrottledClickListener] to the view.
 *
 * Can be used instead of [View.setOnClickListener] to prevent duplicate click events.
 *
 * @receiver Any clickable view.
 * @param onClick Listener that must be guarded from duplicated invocations.
 */
fun View.setOnThrottledClickListener(onClick: () -> Unit) {
    setOnClickListener(object : OnThrottledClickListener() {
        override fun onThrottledClick() {
            onClick()
        }
    })
}

/**
 * Sets [OnThrottledPreferenceClickListener] to the preference.
 *
 * Can be used instead of [Preference.setOnPreferenceClickListener] to prevent duplicate click
 * events.
 *
 * @receiver Any clickable preference.
 * @param onClick Listener that must be guarded from duplicated invocations.
 */
fun Preference.setOnThrottledPreferenceClickListener(onClick: () -> Unit) {
    onPreferenceClickListener = object : OnThrottledPreferenceClickListener() {
        override fun onThrottledClick() {
            onClick()
        }
    }
}