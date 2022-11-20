package me.khruslan.tierlistmaker.tests.data.repositories.dispatchers

import kotlinx.coroutines.Dispatchers
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProviderImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DispatcherProviderTest {

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun init() {
        dispatcherProvider = DispatcherProviderImpl()
    }

    @Test
    fun `Returns default dispatcher`() {
        assertEquals(Dispatchers.Default, dispatcherProvider.default)
    }

    @Test
    fun `Returns io dispatcher`() {
        assertEquals(Dispatchers.IO, dispatcherProvider.io)
    }

    @Test
    fun `Returns main dispatcher`() {
        assertEquals(Dispatchers.Main, dispatcherProvider.main)
    }

    @Test
    fun `Returns unconfined dispatcher`() {
        assertEquals(Dispatchers.Unconfined, dispatcherProvider.unconfined)
    }
}