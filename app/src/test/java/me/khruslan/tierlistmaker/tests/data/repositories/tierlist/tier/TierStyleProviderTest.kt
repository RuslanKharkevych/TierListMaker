package me.khruslan.tierlistmaker.tests.data.repositories.tierlist.tier

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierStyleProviderImpl
import me.khruslan.tierlistmaker.fakes.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.FakeTierColorProvider
import me.khruslan.tierlistmaker.fakes.FakeTierNameProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TierStyleProviderTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `Returns tier styles`() = runTest {
        val colorProvider = FakeTierColorProvider()
        val nameProvider = FakeTierNameProvider()
        val tierStyleProvider = TierStyleProviderImpl(
            colorProvider = colorProvider,
            nameProvider = nameProvider,
            dispatchersProvider = FakeDispatcherProvider()
        )

        colorProvider.fakeColors = listOf(-52429, -26317, -205, -13369549, -13369345)
        nameProvider.fakeNames = mapOf(0 to "S", 1 to "A", 2 to "B", 3 to "C", 4 to "D")

        val expectedStyles = listOf(
            TierStyle(title = "S", color = -52429),
            TierStyle(title = "A", color = -26317),
            TierStyle(title = "B", color = -205),
            TierStyle(title = "C", color = -13369549),
            TierStyle(title = "D", color = -13369345)
        )
        val actualStyles = tierStyleProvider.getTierStyles(5)

        assertEquals(expectedStyles, actualStyles)
    }
}