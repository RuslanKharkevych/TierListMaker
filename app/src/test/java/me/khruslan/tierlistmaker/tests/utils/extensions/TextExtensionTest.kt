package me.khruslan.tierlistmaker.tests.utils.extensions

import me.khruslan.tierlistmaker.utils.extensions.toInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class TextExtensionTest {

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
}