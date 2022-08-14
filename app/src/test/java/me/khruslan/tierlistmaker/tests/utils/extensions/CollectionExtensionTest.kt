package me.khruslan.tierlistmaker.tests.utils.extensions

import me.khruslan.tierlistmaker.dataproviders.utils.ExtensionsDataProvider
import me.khruslan.tierlistmaker.utils.extensions.swap
import me.khruslan.tierlistmaker.utils.extensions.updateLast
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CollectionExtensionTest.ParameterizedSwapIndicesTest::class,
    CollectionExtensionTest.ParameterizedUpdateLastValuesTest::class
)
class CollectionExtensionTest {

    @RunWith(Parameterized::class)
    class ParameterizedSwapIndicesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ExtensionsDataProvider.SwapIndices.data
        }

        @Parameterized.Parameter(ExtensionsDataProvider.SwapIndices.listBeforeSwapParam)
        lateinit var listBeforeSwap: MutableList<Integer>

        @Parameterized.Parameter(ExtensionsDataProvider.SwapIndices.listAfterSwapParam)
        lateinit var listAfterSwap: MutableList<Integer>

        @Parameterized.Parameter(ExtensionsDataProvider.SwapIndices.firstSwapIndexParam)
        lateinit var firstSwapIndex: Integer

        @Parameterized.Parameter(ExtensionsDataProvider.SwapIndices.secondSwapIndexParam)
        lateinit var secondSwapIndex: Integer

        @Test
        fun `Should swap two elements of the mutable list`() {
            listBeforeSwap.swap(firstSwapIndex.toInt(), secondSwapIndex.toInt())
            assertEquals(listAfterSwap, listBeforeSwap)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedUpdateLastValuesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ExtensionsDataProvider.UpdateLastValues.data
        }

        @Parameterized.Parameter(ExtensionsDataProvider.UpdateLastValues.listBeforeUpdateParam)
        lateinit var listBeforeUpdate: MutableList<String>

        @Parameterized.Parameter(ExtensionsDataProvider.UpdateLastValues.listAfterUpdateParam)
        lateinit var listAfterUpdate: MutableList<String>

        @Parameterized.Parameter(ExtensionsDataProvider.UpdateLastValues.valueParam)
        lateinit var value: String

        @Test
        fun `Updates the last element of the mutable list`() {
            listBeforeUpdate.updateLast(value)
            assertEquals(listAfterUpdate, listBeforeUpdate)
        }
    }
}