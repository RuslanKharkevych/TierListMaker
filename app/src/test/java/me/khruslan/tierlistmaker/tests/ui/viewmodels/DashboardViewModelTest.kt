package me.khruslan.tierlistmaker.tests.ui.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.jraska.livedata.test
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.work.UpdateTierListsWorker
import me.khruslan.tierlistmaker.fakes.data.repositories.db.FakePaperRepository
import me.khruslan.tierlistmaker.fakes.data.repositories.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.data.work.FakeUpdateTierListsArgsProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.ui.models.ListState
import me.khruslan.tierlistmaker.ui.navigation.TierListResultException
import me.khruslan.tierlistmaker.ui.viewmodels.DashboardViewModel
import me.khruslan.tierlistmaker.utils.assertAll
import me.khruslan.tierlistmaker.utils.assertEmpty
import me.khruslan.tierlistmaker.utils.assertNotEmpty
import me.khruslan.tierlistmaker.utils.awaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var mockApplication: Application

    private lateinit var fakePaperRepository: FakePaperRepository
    private lateinit var fakeUpdateTierListsArgsProvider: FakeUpdateTierListsArgsProvider
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)
        fakePaperRepository = FakePaperRepository()
        fakeUpdateTierListsArgsProvider = FakeUpdateTierListsArgsProvider()
    }

    private fun TestScope.initViewModel() {
        viewModel = DashboardViewModel(
            application = mockApplication,
            paperRepository = fakePaperRepository,
            dispatcherProvider = FakeDispatcherProvider(),
            updateTierListsArgsProvider = fakeUpdateTierListsArgsProvider
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
        fakePaperRepository.tierLists = mutableListOf()
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to load previews on init`() = runTest {
        fakePaperRepository.tierLists = null
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads empty list of previews on refresh`() = runTest {
        initViewModel()
        fakePaperRepository.tierLists = mutableListOf()
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to refresh previews`() = runTest {
        initViewModel()
        fakePaperRepository.tierLists = null
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads tier list previews on init`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(expectedPreviews)
        viewModel.listStateLiveData.awaitValue(ListState.Filled)
    }

    @Test
    fun `Refreshes previews`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        initViewModel()
        fakePaperRepository.tierLists = tierLists
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(expectedPreviews)
        viewModel.listStateLiveData.awaitValue(ListState.Filled)
    }

    @Test
    fun `Creates new tier list`() = runTest {
        initViewModel()
        val tierListName = "Best shooting guards of all time"
        val tierList = viewModel.createNewTierList(tierListName)

        assertNotEmpty(tierList.id)
        assertEquals(tierListName, tierList.title)
        assertEquals(5, tierList.zoomValue)
        assertEquals(5, tierList.tiers.size)
        assertAll(tierList.tiers) { it.id.isNotEmpty() }
        assertAll(tierList.tiers) { it.images.isEmpty() }
        assertAll(tierList.tiers) { it.style == TierStyle() }
        assertEmpty(tierList.backlogImages)
    }

    @Test
    fun `Returns tier list by position`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()
        val position = 0
        val expectedTierList = tierLists[position]
        val actualTierList = viewModel.getTierListByPosition(position)

        assertEquals(expectedTierList, actualTierList)
    }

    @Test
    fun `Throws error when getting list by incorrect position`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        assertThrows(IndexOutOfBoundsException::class.java) {
            viewModel.getTierListByPosition(tierLists.size)
        }
    }

    @Test
    fun `Notifies UI when new tier list is added`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        val addPreviewObserver = viewModel.addPreviewEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        viewModel.listStateLiveData.awaitValue(ListState.Filled)
        addPreviewObserver.awaitValue(tierLists.size)
    }

    @Test
    fun `Throws TierListResultException if tier list result is null`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        assertThrows(TierListResultException::class.java) {
            viewModel.handleTierListResult(null)
        }
    }

    @Test
    fun `Successfully saves new update to the database`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakePaperRepository.tierLists)
        assertTrue(addedTierList in actualTierLists)
        errorObserver.assertNoValue()
    }

    @Test
    fun `Shows error when unable to save new tier list to the database`() = runTest {
        fakePaperRepository.tierLists = tierLists
        fakePaperRepository.shouldSaveSuccessfully = false
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakePaperRepository.tierLists)
        assertTrue(addedTierList !in actualTierLists)
        errorObserver.awaitValue(R.string.save_tier_list_error_message)
    }

    @Test
    fun `Notifies UI when tier list is updated`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        val updatePreviewObserver = viewModel.updatePreviewEvent.test()
        val updatedTierListPosition = tierLists.indexOfFirst { it.id == updatedTierList.id }
        viewModel.handleTierListResult(updatedTierList)
        advanceUntilIdle()

        updatePreviewObserver.awaitValue(updatedTierListPosition)
    }

    @Test
    fun `Swaps tier lists`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()
        viewModel.swapTierLists(0, 1)

        assertEquals(tierLists[0], viewModel.getTierListByPosition(1))
        assertEquals(tierLists[1], viewModel.getTierListByPosition(0))
    }

    @Test
    fun `Successfully removes tier list`() = runTest {
        fakePaperRepository.tierLists = tierLists
        initViewModel()

        val tierListIndex = 1
        val tierList = tierLists[tierListIndex]
        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakePaperRepository.tierLists)
        assertTrue(tierList !in actualTierLists)
        errorObserver.assertNoValue()
    }

    @Test
    fun `When last tier list is removed sets empty list state`() = runTest {
        val tierListIndex = 0
        val tierList = tierLists[tierListIndex]
        fakePaperRepository.tierLists = mutableListOf(tierList)
        initViewModel()

        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakePaperRepository.tierLists)
        assertTrue(actualTierLists.isEmpty())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
        errorObserver.assertNoValue()
    }

    @Test
    fun `Shows error when unable to update tier lists in the database`() = runTest {
        fakePaperRepository.tierLists = tierLists
        fakePaperRepository.shouldRemoveSuccessfully = false
        initViewModel()

        val tierListIndex = 1
        val tierList = tierLists[tierListIndex]
        val errorObserver = viewModel.errorEvent.test()
        viewModel.removeTierList(tierListIndex)
        advanceUntilIdle()

        val actualTierLists = checkNotNull(fakePaperRepository.tierLists)
        assertTrue(tierList in actualTierLists)
        errorObserver.awaitValue(R.string.remove_tier_list_error_message)
    }

    @Test
    fun `Enqueues work to update tier lists`() = runTest {
        mockkObject(OneTimeWorkRequest)
        mockkStatic(WorkManager::class)
        val mockWorkRequest: OneTimeWorkRequest = mockk()
        val mockWorkManager: WorkManager = mockk()
        every { OneTimeWorkRequest.from(UpdateTierListsWorker::class.java) } returns mockWorkRequest
        every { WorkManager.getInstance(mockApplication) } returns mockWorkManager
        every { mockWorkManager.enqueue(mockWorkRequest) } returns mockk()

        fakePaperRepository.tierLists = tierLists
        initViewModel()
        viewModel.enqueueUpdateTierListsWork()

        assertEquals(tierLists, fakeUpdateTierListsArgsProvider.tierLists)
        verify { mockWorkManager.enqueue(mockWorkRequest) }
        unmockkObject(OneTimeWorkRequest)
        unmockkStatic(WorkManager::class)
    }
}