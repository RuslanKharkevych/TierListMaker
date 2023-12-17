package me.khruslan.tierlistmaker.tests.data.providers.db

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.paperdb.Book
import io.paperdb.Paper
import io.paperdb.PaperDbException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.providers.db.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.db.DatabaseHelperImpl
import me.khruslan.tierlistmaker.fakes.data.providers.db.FakeDefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.fakes.data.providers.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.utils.performance.NoOpPerformanceService
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import org.junit.*
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class DatabaseHelperTest {

    companion object {
        private const val KEY_TIER_LISTS = "tier-lists"
    }

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var book: Book

    private lateinit var fakeDefaultTierListCollectionProvider: FakeDefaultTierListCollectionProvider
    private lateinit var databaseHelper: DatabaseHelper

    private val tierLists
        get() = mutableListOf(
            TierList(
                id = "62cacbfa-b8fb-11ec-8422-0242ac120002",
                title = "Favorite dishes",
                zoomValue = 4,
                tiers = mutableListOf(
                    Tier(
                        id = "b4b864b8-16dd-47ca-85ae-ee4f96ed44d4",
                        images = mutableListOf(
                            StorageImage(
                                id = "67cae7d3-1d75-495f-8410-34d390cff96a",
                                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
                            )
                        )
                    ),
                    Tier(
                        id = "ab2a1957-0743-4812-b20c-5816002155a1",
                        images = mutableListOf(
                            StorageImage(
                                id = "eaff63e8-4229-4461-b252-d788404c1a90",
                                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
                            )
                        )
                    )
                ),
                backlogImages = mutableListOf(
                    StorageImage(
                        id = "401470da-6034-4e48-a53b-37e23834c897",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
                    ),
                    StorageImage(
                        id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9226934535226.png"
                    )
                )
            ),
            TierList(
                id = "b42d6153-bd49-4a58-b1ba-e8ee6e405d8c",
                title = "Programming languages",
                backlogImages = mutableListOf(),
                tiers = mutableListOf(),
                zoomValue = 7
            )
        )

    private val newTierList = TierList(
        id = "eaff63e8-4229-4461-b252-d788404c1a90",
        title = "Best movies",
        zoomValue = 5,
        tiers = mutableListOf(
            Tier(
                id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
                images = mutableListOf()
            )
        ),
        backlogImages = mutableListOf(
            StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
            )
        )
    )

    private val updatedTierList = TierList(
        id = "62cacbfa-b8fb-11ec-8422-0242ac120002",
        title = "Favorite dishes",
        zoomValue = 4,
        tiers = mutableListOf(
            Tier(
                id = "b4b864b8-16dd-47ca-85ae-ee4f96ed44d4",
                images = mutableListOf(
                    StorageImage(
                        id = "67cae7d3-1d75-495f-8410-34d390cff96a",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
                    ),
                    StorageImage(
                        id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9226934535226.png"
                    )
                )
            ),
            Tier(
                id = "ab2a1957-0743-4812-b20c-5816002155a1",
                images = mutableListOf(
                    StorageImage(
                        id = "eaff63e8-4229-4461-b252-d788404c1a90",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
                    ),
                    StorageImage(
                        id = "401470da-6034-4e48-a53b-37e23834c897",
                        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
                    )
                )
            )
        ),
        backlogImages = mutableListOf()
    )

    private val updatedTierLists
        get() = mutableListOf(updatedTierList, tierLists[1])

    private val tierListToRemove
        get() = tierLists[0]

    @Before
    fun init() {
        MockKAnnotations.init(this)
        mockkStatic(Paper::class)
        every { Paper.book() } returns book

        val dispatcherProvider = FakeDispatcherProvider()
        fakeDefaultTierListCollectionProvider = FakeDefaultTierListCollectionProvider()
        databaseHelper = DatabaseHelperImpl(
            dispatcherProvider = dispatcherProvider,
            defaultTierListCollectionProvider = fakeDefaultTierListCollectionProvider,
            performanceService = NoOpPerformanceService()
        )
    }

    @After
    fun release() {
        unmockkStatic(Paper::class)
    }

    @Test
    fun `Successfully gets tier lists`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        assertEquals(tierLists, databaseHelper.getTierLists())
    }

    @Test
    fun `Gets tier lists on retry attempt`() = runTest {
        val exception = PaperDbException("Couldn't read file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } throws exception andThen tierLists

        assertEquals(tierLists, databaseHelper.getTierLists())
    }

    @Test
    fun `Fails to get tier lists`() = runTest {
        val exception = PaperDbException("Couldn't read file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } throws exception

        assertNull(databaseHelper.getTierLists())
    }

    @Test
    fun `Provides default tier list collection if was not provided before`() = runTest {
        fakeDefaultTierListCollectionProvider.tierLists = tierLists
        val answers = listOf(mutableListOf(), fakeDefaultTierListCollectionProvider.tierLists)
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returnsMany answers
        every { book.write(KEY_TIER_LISTS, answers.last()) } returns book

        assertEquals(tierLists, databaseHelper.getTierLists())
    }

    @Test
    fun `Doesn't provide default tier list collection if was provided before`() = runTest {
        fakeDefaultTierListCollectionProvider.tierLists = tierLists
        fakeDefaultTierListCollectionProvider.provideCollection()
        val answers = listOf(mutableListOf(), fakeDefaultTierListCollectionProvider.tierLists)
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returnsMany answers

        assertEquals(mutableListOf<TierList>(),  databaseHelper.getTierLists())
    }

    @Test
    fun `Doesn't save tier list if unable to get them`() = runTest {
        val exception = PaperDbException("Couldn't read file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } throws exception

        assertFalse(databaseHelper.saveTierList(newTierList))
    }

    @Test
    fun `Saves new tier list`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, tierLists + newTierList) } returns book

        assertTrue(databaseHelper.saveTierList(newTierList))
    }

    @Test
    fun `Saves new tier list on retry attempt`() = runTest {
        val exception = PaperDbException("Couldn't save file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, tierLists + newTierList) } throws exception andThen book

        assertTrue(databaseHelper.saveTierList(newTierList))
    }

    @Test
    fun `Fails to save new tier list`() = runTest {
        val exception = PaperDbException("Couldn't save file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, tierLists + newTierList) } throws exception

        assertFalse(databaseHelper.saveTierList(newTierList))
    }

    @Test
    fun `Saves updated tier list`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, updatedTierLists) } returns book

        assertTrue(databaseHelper.saveTierList(updatedTierList))
    }

    @Test
    fun `Saves updated tier list on retry attempt`() = runTest {
        val exception = PaperDbException("Couldn't save file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, updatedTierLists) } throws exception andThen book

        assertTrue(databaseHelper.saveTierList(updatedTierList))
    }

    @Test
    fun `Fails to save updated tier list`() = runTest {
        val exception = PaperDbException("Couldn't save file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, updatedTierLists) } throws exception

        assertFalse(databaseHelper.saveTierList(updatedTierList))
    }

    @Test
    fun `Doesn't remove tier list if unable to get them`() = runTest {
        val exception = PaperDbException("Couldn't read file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } throws exception

        assertFalse(databaseHelper.removeTierListById(tierListToRemove.id))
    }

    @Test
    fun `Doesn't remove tier list if unable to find it`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        assertFalse(databaseHelper.removeTierListById("invalid_id"))
    }

    @Test
    fun `Removes tier list by id`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, tierLists - tierListToRemove) } returns book

        assertTrue(databaseHelper.removeTierListById(tierListToRemove.id))
    }

    @Test
    fun `Fails to update tier lists`() = runTest {
        val exception = PaperDbException("Couldn't save file")
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, updatedTierLists) } throws exception

        assertFalse(databaseHelper.updateTierLists(updatedTierLists))
    }

    @Test
    fun `Updates tier lists`() = runTest {
        every { book.read(KEY_TIER_LISTS, mutableListOf<TierList>()) } returns tierLists
        every { book.write(KEY_TIER_LISTS, updatedTierLists) } returns book

        assertTrue(databaseHelper.updateTierLists(updatedTierLists))
    }
}