package me.khruslan.tierlistmaker.repository.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Contract for providing [CoroutineDispatcher].
 */
interface DispatcherProvider {

    /**
     * Wrapper for [Dispatchers.Default].
     */
    val default: CoroutineDispatcher

    /**
     * Wrapper for [Dispatchers.IO].
     */
    val io: CoroutineDispatcher

    /**
     * Wrapper for [Dispatchers.Main].
     */
    val main: CoroutineDispatcher

    /**
     * Wrapper for [Dispatchers.Unconfined]
     */
    val unconfined: CoroutineDispatcher
}