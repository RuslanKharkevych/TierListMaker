package me.khruslan.tierlistmaker.repository.dispatchers

import kotlinx.coroutines.Dispatchers

/**
 * [DispatcherProvidable] implementation.
 */
class DispatcherProvider : DispatcherProvidable {
    override val default get() = Dispatchers.Default
    override val io get() = Dispatchers.IO
    override val main get() = Dispatchers.Main
    override val unconfined get() = Dispatchers.Unconfined
}