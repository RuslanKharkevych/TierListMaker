package me.khruslan.tierlistmaker.tests.data.providers.database

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProviderImpl
import me.khruslan.tierlistmaker.fakes.android.MockAssetManager
import me.khruslan.tierlistmaker.fakes.data.providers.database.FakePreferencesHelper
import me.khruslan.tierlistmaker.fakes.utils.performance.NoOpPerformanceService
import me.khruslan.tierlistmaker.utils.assertAll
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DefaultTierListCollectionProviderTest {

    @MockK
    private lateinit var mockContext: Context

    private lateinit var fakePreferencesHelper: FakePreferencesHelper
    private lateinit var defaultTierListCollectionProvider: DefaultTierListCollectionProvider

    @Before
    fun init() {
        MockKAnnotations.init(this)
        fakePreferencesHelper = FakePreferencesHelper()
        defaultTierListCollectionProvider = DefaultTierListCollectionProviderImpl(
            context = mockContext,
            preferencesHelper = fakePreferencesHelper,
            performanceService = NoOpPerformanceService()
        )
    }

    @Test
    fun `Verify collection is not provided if it wasn't marked as provided`() {
        assertFalse(defaultTierListCollectionProvider.collectionProvided)
    }

    @Test
    fun `Verify collection is provided if it was marked as provided`() {
        fakePreferencesHelper.markDefaultTierListCollectionAsProvided()
        assertTrue(defaultTierListCollectionProvider.collectionProvided)
    }

    @Test
    fun `Provides collection and marks it as provided`() {
        every { mockContext.getString(any()) } returns "Dummy title"
        every { mockContext.assets } returns MockAssetManager.get()
        val collection = defaultTierListCollectionProvider.provideCollection()

        assertAll(MockAssetManager.assets) { collection.contains(it) }
        assertTrue(fakePreferencesHelper.defaultTierListCollectionProvided)
    }

    private fun MutableList<TierList>.contains(imageFilePath: String): Boolean {
        return flatMap { it.tiers }
            .flatMap { it.images }
            .any { it.payload == imageFilePath }
    }
}