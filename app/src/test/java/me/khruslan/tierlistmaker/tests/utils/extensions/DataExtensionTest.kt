package me.khruslan.tierlistmaker.tests.utils.extensions

import androidx.lifecycle.SavedStateHandle
import me.khruslan.tierlistmaker.dataproviders.utils.ExtensionsDataProvider.InvalidSavedStateHandleKeys
import me.khruslan.tierlistmaker.dataproviders.utils.ExtensionsDataProvider.ValidSavedStateHandleKeys
import me.khruslan.tierlistmaker.utils.extensions.require
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DataExtensionTest.ParameterizedValidSavedStateHandleKeysTest::class,
    DataExtensionTest.ParameterizedInvalidSavedStateHandleKeysTest::class
)
class DataExtensionTest {

    @RunWith(Parameterized::class)
    class ParameterizedValidSavedStateHandleKeysTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ValidSavedStateHandleKeys.data
        }

        @Parameterized.Parameter(ValidSavedStateHandleKeys.savedStateHandleParam)
        lateinit var savedStateHandle: SavedStateHandle

        @Parameterized.Parameter(ValidSavedStateHandleKeys.keyParam)
        lateinit var key: String

        @Parameterized.Parameter(ValidSavedStateHandleKeys.valueParam)
        lateinit var value: String

        @Test
        fun `Returns value by required key from SavedStateHandle`() {
            assertEquals(value, savedStateHandle.require(key))
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedInvalidSavedStateHandleKeysTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = InvalidSavedStateHandleKeys.data
        }

        @Parameterized.Parameter(InvalidSavedStateHandleKeys.savedStateHandleParam)
        lateinit var savedStateHandle: SavedStateHandle

        @Parameterized.Parameter(InvalidSavedStateHandleKeys.keyParam)
        lateinit var key: String

        @Test
        fun `Throws IllegalArgumentException if SavedStateHandle doesn't contain required key`() {
            assertThrows(IllegalArgumentException::class.java) {
                savedStateHandle.require<String>(key)
            }
        }
    }
}