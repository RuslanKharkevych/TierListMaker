package me.khruslan.tierlistmaker.data.providers.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * [DispatcherProvider] implementation.
 *
 * Can be injected in production code.
 *
 * @constructor Creates a new dispatcher provider instance.
 */
class DispatcherProviderImpl @Inject constructor(): DispatcherProvider {

    /**
     * Returns [Dispatchers.Default].
     */
    override val default get() = Dispatchers.Default

    /**
     * Returns [Dispatchers.IO].
     */
    override val io get() = Dispatchers.IO

    /**
     * Returns [Dispatchers.Main].
     */
    override val main get() = Dispatchers.Main

    /**
     * Returns [Dispatchers.Unconfined].
     */
    override val unconfined get() = Dispatchers.Unconfined
}