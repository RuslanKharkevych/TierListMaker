package me.khruslan.tierlistmaker.tests.ui.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.fakes.data.providers.database.FakeDatabaseHelper
import me.khruslan.tierlistmaker.fakes.data.providers.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.data.providers.tierlist.FakeTierListCreator
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.presentation.models.ListState
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultException
import me.khruslan.tierlistmaker.presentation.viewmodels.CollectionViewModel
import me.khruslan.tierlistmaker.utils.awaitValue
import me.khruslan.tierlistmaker.util.swap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CollectionViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var mockApplication: Application

    private lateinit var fakeDatabaseHelper: FakeDatabaseHelper
    private lateinit var fakeTierListCreator: FakeTierListCreator
    private lateinit var viewModel: CollectionViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)
        fakeDatabaseHelper = FakeDatabaseHelper()
        fakeTierListCreator = FakeTierListCreator()
    }

    private fun TestScope.initViewModel() {
        viewModel = CollectionViewModel(
            application = mockApplication,
            databaseHelper = fakeDatabaseHelper,
            dispatcherProvider = FakeDispatcherProvider(),
            tierListCreator = fakeTierListCreator
        )

        advanceUntilIdle()
    }

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
                id = "8db2ed89-a066-4d83-afb7-82f98e24e88e",
                title = "Most talented vocalists",
                backlogImages = mutableListOf(),
                tiers = mutableListOf(),
                zoomValue = 4
            )
        )

    private val addedTierList
        get() = TierList(
            id = "b42d6153-bd49-4a58-b1ba-e8ee6e405d8c",
            title = "Programming languages",
            backlogImages = mutableListOf(),
            tiers = mutableListOf(),
            zoomValue = 7
        )

    private val updatedTierList
        get() = TierList(
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
                ),
                StorageImage(
                    id = "eaff63e8-4229-4461-b252-d788404c1a90",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
                )
            )
        )

    @Test
    fun `Loads empty list of previews on init`() = runTest {
        fakeDatabaseHelper.tierLists = mutableListOf()
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to load previews on init`() = runTest {
        fakeDatabaseHelper.tierLists = null
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads empty list of previews on refresh`() = runTest {
        initViewModel()
        fakeDatabaseHelper.tierLists = mutableListOf()
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to refresh previews`() = runTest {
        initViewModel()
        fakeDatabaseHelper.tierLists = null
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads tier list previews on init`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(expectedPreviews)
        viewModel.listStateLiveData.awaitValue(ListState.Filled)
    }

    @Test
    fun `Refreshes previews`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        initViewModel()
        fakeDatabaseHelper.tierLists = tierLists
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(expectedPreviews)
        viewModel.listStateLiveData.awaitValue(ListState.Filled)
    }

    @Test
    fun `Creates new tier list`() = runTest {
        fakeTierListCreator.tierLists = listOf(addedTierList)
        initViewModel()

        val tierListCreatedObserver = viewModel.tierListCreatedEvent.test()
        viewModel.createNewTierList(addedTierList.title)
        advanceUntilIdle()

        tierListCreatedObserver.awaitValue(addedTierList)
    }

    @Test
    fun `Returns tier list by position`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()
        val position = 0
        val expectedTierList = tierLists[position]
        val actualTierList = viewModel.getTierListByPosition(position)

        assertEquals(expectedTierList, actualTierList)
    }

    @Test
    fun `Throws error when getting list by incorrect position`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        assertThrows(IndexOutOfBoundsException::class.java) {
            viewModel.getTierListByPosition(tierLists.size)
        }
    }

    @Test
    fun `Notifies UI when new tier list is added`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        val addPreviewObserver = viewModel.addPreviewEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        viewModel.listStateLiveData.awaitValue(ListState.Filled)
        addPreviewObserver.awaitValue(Unit)
    }

    @Test
    fun `Throws TierListResultException if tier list result is null`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        assertThrows(TierListResultException::class.java) {
            viewModel.handleTierListResult(null)
        }
    }

    @Test
    fun `Successfully saves new update to the database`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertTrue(addedTierList in actualTierLists)
        errorObserver.assertNoValue()
    }

    @Test
    fun `Shows error when unable to save new tier list to the database`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        fakeDatabaseHelper.shouldSaveSuccessfully = false
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertTrue(addedTierList !in actualTierLists)
        errorObserver.awaitValue(R.string.save_tier_list_error_message)
    }

    @Test
    fun `Notifies UI when tier list is updated`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        val updatePreviewObserver = viewModel.updatePreviewEvent.test()
        val updatedTierListPosition = tierLists.indexOfFirst { it.id == updatedTierList.id }
        viewModel.handleTierListResult(updatedTierList)
        advanceUntilIdle()

        updatePreviewObserver.awaitValue(updatedTierListPosition)
    }

    @Test
    fun `Swaps tier lists and successfully updates them in the database`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.swapTierLists(0, 1)
        advanceUntilIdle()

        val expectedTierLists = tierLists.apply { swap(0, 1) }
        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertEquals(expectedTierLists, actualTierLists)
        assertEquals(tierLists[0], viewModel.getTierListByPosition(1))
        assertEquals(tierLists[1], viewModel.getTierListByPosition(0))
        errorObserver.assertNoValue()
    }

    @Test
    fun `Swaps tier lists and shows error if unable to update them in the database`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        fakeDatabaseHelper.shouldUpdateSuccessfully = false
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.swapTierLists(0, 1)
        advanceUntilIdle()

        val expectedTierLists = tierLists
        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertEquals(expectedTierLists, actualTierLists)
        assertEquals(tierLists[0], viewModel.getTierListByPosition(1))
        assertEquals(tierLists[1], viewModel.getTierListByPosition(0))
        errorObserver.awaitValue(R.string.update_tier_lists_error_message)
    }

    @Test
    fun `Successfully removes tier list`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        initViewModel()

        val tierListIndex = 1
        val tierList = tierLists[tierListIndex]
        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertTrue(tierList !in actualTierLists)
        errorObserver.assertNoValue()
    }

    @Test
    fun `When last tier list is removed sets empty list state`() = runTest {
        val tierListIndex = 0
        val tierList = tierLists[tierListIndex]
        fakeDatabaseHelper.tierLists = mutableListOf(tierList)
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertTrue(actualTierLists.isEmpty())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
        errorObserver.assertNoValue()
    }

    @Test
    fun `Shows error when unable to update tier lists in the database`() = runTest {
        fakeDatabaseHelper.tierLists = tierLists
        fakeDatabaseHelper.shouldRemoveSuccessfully = false
        initViewModel()

        val tierListIndex = 1
        val tierList = tierLists[tierListIndex]
        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakeDatabaseHelper.tierLists)
        assertTrue(tierList in actualTierLists)
        errorObserver.awaitValue(R.string.remove_tier_list_error_message)
    }
}