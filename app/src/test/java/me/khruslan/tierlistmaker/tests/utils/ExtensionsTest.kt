package me.khruslan.tierlistmaker.tests.utils

import androidx.lifecycle.SavedStateHandle
import me.khruslan.tierlistmaker.utils.require
import me.khruslan.tierlistmaker.utils.swap
import me.khruslan.tierlistmaker.utils.toInt
import me.khruslan.tierlistmaker.utils.updateLast
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `When CharSequence can't be converted to Int throws NumberFormatException`() {
        val charSequence = "Milk" as CharSequence
        assertThrows(NumberFormatException::class.java) {
            charSequence.toInt()
        }
    }

    @Test
    fun `Converts CharSequence to Int`() {
        val charSequence = "75" as CharSequence
        val expectedInt = 75

        assertEquals(expectedInt, charSequence.toInt())
    }

    @Test
    fun `Should swap two elements of the mutable list`() {
        val expectedList = mutableListOf(1, 4, 3, 2, 5)
        val actualList = mutableListOf(1, 2, 3, 4, 5)
        actualList.swap(1, 3)

        assertEquals(expectedList, actualList)
    }

    @Test
    fun `Updates the last element of the mutable list`() {
        val expectedList = mutableListOf('a', 'b', 'd')
        val actualList = mutableListOf('a', 'b', 'c')
        actualList.updateLast('d')

        assertEquals(expectedList, actualList)
    }

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