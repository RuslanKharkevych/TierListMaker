package me.khruslan.tierlistmaker.util.log.timber

import android.util.Log
import timber.log.Timber

/**
 * [Timber.Tree] implementation for debug builds.
 *
 * Logs messages to the console. Unlike [Timber.DebugTree], uses a single tag for all logs.
 *
 * @constructor Creates a new debug tree.
 */
class DebugTimberTree : Timber.DebugTree() {

    /**
     * Constants for internal usage.
     */
    private companion object Constants {

        /**
         * The tag name used for all logs.
         */
        private const val LOG_TAG = "TierListMaker"
    }

    /**
     * Writes a log message to its destination.
     *
     * Overrides [tag] with [LOG_TAG]. Called for all level-specific methods by default.
     *
     * @param priority Log level. See [Log] for constants.
     * @param tag Explicit or inferred tag. Always ignored.
     * @param message Formatted log message.
     * @param t Accompanying exception. May be null.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, LOG_TAG, message, t)
    }
}