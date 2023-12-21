package me.khruslan.tierlistmaker.util.log.timber

import timber.log.Timber

/**
 * [Timber.Tree] implementation for debug builds. Logs messages to the console.
 */
class DebugTimberTree : Timber.DebugTree() {

    /**
     * Companion object of the [DebugTimberTree] that stores global log tag.
     */
    private companion object {
        private const val LOG_TAG = "TierListMaker"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, LOG_TAG, message, t)
    }
}