package me.khruslan.tierlistmaker.tests.utils

import me.khruslan.tierlistmaker.util.swap
import me.khruslan.tierlistmaker.util.updateLast
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

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
}