package me.khruslan.tierlistmaker.tests.utils.extensions

import androidx.lifecycle.SavedStateHandle
import me.khruslan.tierlistmaker.utils.extensions.require
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class DataExtensionTest {

    @Test
    fun `Returns value by required key from SavedStateHandle`() {
        val key = "testKey"
        val value = "testValue"
        val savedStateHandle = SavedStateHandle(mapOf(key to value))

        assertEquals(value, savedStateHandle.require(key))
    }

    @Test
    fun `Throws IllegalArgumentException if SavedStateHandle doesn't contain required key`() {
        val savedStateHandle = SavedStateHandle()
        assertThrows(IllegalArgumentException::class.java) {
            savedStateHandle.require<String>("testKey")
        }
    }
}