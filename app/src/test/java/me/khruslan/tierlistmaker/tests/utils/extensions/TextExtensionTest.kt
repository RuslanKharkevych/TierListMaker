package me.khruslan.tierlistmaker.tests.utils.extensions

import me.khruslan.tierlistmaker.dataproviders.utils.ExtensionsDataProvider
import me.khruslan.tierlistmaker.utils.extensions.toInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TextExtensionTest.ParameterizedInvalidNumberCharSequencesTest::class,
    TextExtensionTest.ParameterizedConvertedIntegersTest::class
)
class TextExtensionTest {

    @RunWith(Parameterized::class)
    class ParameterizedInvalidNumberCharSequencesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ExtensionsDataProvider.invalidNumberCharSequences
        }

        @Parameterized.Parameter
        lateinit var charSequence: CharSequence

        @Test
        fun `When CharSequence can't be converted to Int throws NumberFormatException`() {
            assertThrows(NumberFormatException::class.java) {
                charSequence.toInt()
            }
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedConvertedIntegersTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ExtensionsDataProvider.ConvertedIntegers.data
        }

        @Parameterized.Parameter(ExtensionsDataProvider.ConvertedIntegers.charSequenceParam)
        lateinit var charSequence: CharSequence

        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        @Parameterized.Parameter(ExtensionsDataProvider.ConvertedIntegers.integerParam)
        lateinit var integer: Integer

        @Test
        fun `Converts CharSequence to Int`() {
            assertEquals(integer, charSequence.toInt())
        }
    }
}