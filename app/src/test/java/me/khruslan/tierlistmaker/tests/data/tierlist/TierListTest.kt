package me.khruslan.tierlistmaker.tests.data.tierlist

import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.dataproviders.data.TierListDataProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import kotlin.test.assertEquals

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TierListTest.StandardTest::class,
    TierListTest.ParameterizedTierListPreviewsTest::class
)
class TierListTest {

    class StandardTest {

        @Test
        fun `TierList class has correct qualified name`() {
            val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.TierList"
            val actualQualifiedName = TierList::class.qualifiedName

            assertEquals(expectedQualifiedName, actualQualifiedName)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedTierListPreviewsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.Previews.data
        }

        @Parameterized.Parameter(TierListDataProvider.Previews.listParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(TierListDataProvider.Previews.previewParam)
        lateinit var preview: TierList.Preview

        @Test
        fun `Creates preview of the tier list`() {
            assertEquals(preview, tierList.preview)
        }
    }
}