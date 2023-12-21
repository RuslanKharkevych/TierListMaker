package me.khruslan.tierlistmaker.presentation.utils

import android.view.View
import androidx.preference.Preference

/**
 * Implements throttling mechanism to prevent duplicated click events.
 */
private abstract class ClickThrottler {

    /**
     * The timestamp of the last click.
     */
    private var lastClickTimestamp = 0L

    /**
     * Companion object of [ClickThrottler].
     */
    private companion object {
        private const val MIN_CLICK_INTERVAL_MILLIS = 2000L
    }

    /**
     * Invoked when click event is allowed.
     */
    abstract fun onThrottledClick()

    /**
     * Throttles the click event. If the click is allowed, returns true and invokes
     * [onThrottledClick] listener. Otherwise returns false without taking action.
     *
     * @return whether the click event has been allowed.
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
 */
private abstract class OnThrottledClickListener : ClickThrottler(), View.OnClickListener {
    final override fun onClick(v: View?) {
        throttleClick()
    }
}

/**
 * Extension of [Preference.OnPreferenceClickListener] that prevents multiple clicks.
 */
private abstract class OnThrottledPreferenceClickListener : ClickThrottler(),
    Preference.OnPreferenceClickListener {
    final override fun onPreferenceClick(preference: Preference): Boolean {
        return throttleClick()
    }
}

/**
 * Sets [OnThrottledClickListener] to the view.
 *
 * @receiver any clickable view.
 * @param onClick listener that must be guarded from duplicated invocations.
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
 * @receiver any clickable preference.
 * @param onClick listener that must be guarded from duplicated invocations.
 */
fun Preference.setOnThrottledPreferenceClickListener(onClick: () -> Unit) {
    onPreferenceClickListener = object : OnThrottledPreferenceClickListener() {
        override fun onThrottledClick() {
            onClick()
        }
    }
}