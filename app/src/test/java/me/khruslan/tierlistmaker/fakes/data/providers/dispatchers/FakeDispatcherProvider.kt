package me.khruslan.tierlistmaker.fakes.data.providers.dispatchers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider

@ExperimentalCoroutinesApi
class FakeDispatcherProvider : DispatcherProvider {
    override val default get() = StandardTestDispatcher()
    override val io get() = StandardTestDispatcher()
    override val main get() = StandardTestDispatcher()
    override val unconfined get() = UnconfinedTestDispatcher()
}