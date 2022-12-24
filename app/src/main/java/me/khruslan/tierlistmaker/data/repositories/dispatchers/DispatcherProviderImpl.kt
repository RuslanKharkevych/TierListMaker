package me.khruslan.tierlistmaker.data.repositories.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * [DispatcherProvider] implementation.
 */
class DispatcherProviderImpl @Inject constructor(): DispatcherProvider {
    override val default get() = Dispatchers.Default
    override val io get() = Dispatchers.IO
    override val main get() = Dispatchers.Main
    override val unconfined get() = Dispatchers.Unconfined
}