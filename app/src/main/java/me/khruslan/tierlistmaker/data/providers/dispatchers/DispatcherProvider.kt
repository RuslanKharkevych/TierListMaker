package me.khruslan.tierlistmaker.data.providers.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Contract for providing coroutine dispatchers.
 *
 * Used for injecting test dispatcher in unit tests.
 */
interface DispatcherProvider {

    /**
     * Returns [Dispatchers.Default] in production code.
     */
    val default: CoroutineDispatcher

    /**
     * Returns [Dispatchers.IO] in production code.
     */
    val io: CoroutineDispatcher

    /**
     * Returns [Dispatchers.Main] in production code.
     */
    val main: CoroutineDispatcher

    /**
     * Returns [Dispatchers.Unconfined] in production code.
     */
    val unconfined: CoroutineDispatcher
}