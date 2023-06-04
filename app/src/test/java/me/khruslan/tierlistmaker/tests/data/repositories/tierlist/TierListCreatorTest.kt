package me.khruslan.tierlistmaker.tests.data.repositories.tierlist

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListCreator
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListCreatorImpl
import me.khruslan.tierlistmaker.fakes.data.repositories.db.FakePreferencesHelper
import me.khruslan.tierlistmaker.fakes.data.repositories.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.assertAll
import me.khruslan.tierlistmaker.utils.assertEmpty
import me.khruslan.tierlistmaker.utils.assertNotEmpty
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TierListCreatorTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var fakePreferencesHelper: FakePreferencesHelper
    private lateinit var tierListCreator: TierListCreator

    @Before
    fun init() {
        fakePreferencesHelper = FakePreferencesHelper()
        tierListCreator = TierListCreatorImpl(
            dispatcherProvider = FakeDispatcherProvider(),
            preferencesHelper = fakePreferencesHelper
        )
    }

    @Test
    fun `Creates new tier list`() = runTest {
        val title = "Best vacation destinations"
        val expectedZoomValue = fakePreferencesHelper.scale + 1
        val expectedTiersCount = fakePreferencesHelper.tiersCount
        val tierList = tierListCreator.newTierList(title)

        assertNotEmpty(tierList.id)
        assertEquals(title, tierList.title)
        assertEquals(expectedZoomValue, tierList.zoomValue)
        assertEquals(expectedTiersCount, tierList.tiers.size)
        assertAll(tierList.tiers) { it.id.isNotEmpty() }
        assertAll(tierList.tiers) { it.images.isEmpty() }
        assertAll(tierList.tiers) { it.style == TierStyle() }
        assertEmpty(tierList.backlogImages)
    }
}