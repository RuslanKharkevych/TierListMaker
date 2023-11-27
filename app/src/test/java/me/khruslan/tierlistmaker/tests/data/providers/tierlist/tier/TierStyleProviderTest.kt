package me.khruslan.tierlistmaker.tests.data.providers.tierlist.tier

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierStyleProviderImpl
import me.khruslan.tierlistmaker.fakes.data.providers.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier.FakeTierColorProvider
import me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier.FakeTierNameProvider
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

        colorProvider.colors = listOf(-52429, -26317, -205, -13369549, -13369345)
        nameProvider.names = listOf("S", "A", "B", "C", "D")

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