package me.khruslan.tierlistmaker.tests.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.ui.models.ListState
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.fakes.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.FakePaperRepository
import me.khruslan.tierlistmaker.ui.navigation.TierListResultException
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.assertAll
import me.khruslan.tierlistmaker.utils.assertEmpty
import me.khruslan.tierlistmaker.utils.assertNotEmpty
import me.khruslan.tierlistmaker.utils.awaitValue
import me.khruslan.tierlistmaker.ui.viewmodels.DashboardViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var fakePaperRepository: FakePaperRepository
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun initFakePaperRepository() {
        fakePaperRepository = FakePaperRepository()
    }

    private fun TestScope.initViewModel() {
        viewModel = DashboardViewModel(
            paperRepository = fakePaperRepository,
            dispatcherProvider = FakeDispatcherProvider()
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
        fakePaperRepository.fakeTierLists = mutableListOf()
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to load previews on init`() = runTest {
        fakePaperRepository.fakeTierLists = null
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads empty list of previews on refresh`() = runTest {
        initViewModel()
        fakePaperRepository.fakeTierLists = mutableListOf()
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Empty)
    }

    @Test
    fun `Fails to refresh previews`() = runTest {
        initViewModel()
        fakePaperRepository.fakeTierLists = null
        viewModel.refreshPreviews()

        viewModel.listStateLiveData.awaitValue(ListState.Loading)
        advanceUntilIdle()
        viewModel.tierListPreviewsLiveData.awaitValue(mutableListOf())
        viewModel.listStateLiveData.awaitValue(ListState.Failed)
    }

    @Test
    fun `Loads tier list previews on init`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        viewModel.tierListPreviewsLiveData.awaitValue(expectedPreviews)
        viewModel.listStateLiveData.awaitValue(ListState.Filled)
    }

    @Test
    fun `Refreshes previews`() = runTest {
        val expectedPreviews = tierLists.map { it.preview }.toMutableList()
        initViewModel()
        fakePaperRepository.fakeTierLists = tierLists
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
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()
        val position = 0
        val expectedTierList = tierLists[position]
        val actualTierList = viewModel.getTierListByPosition(position)

        assertEquals(expectedTierList, actualTierList)
    }

    @Test
    fun `Throws error when getting list by incorrect position`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        assertThrows(IndexOutOfBoundsException::class.java) {
            viewModel.getTierListByPosition(tierLists.size)
        }
    }

    @Test
    fun `Notifies UI when new tier list is added`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        val addPreviewObserver = viewModel.addPreviewEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        viewModel.listStateLiveData.awaitValue(ListState.Filled)
        addPreviewObserver.awaitValue(tierLists.size)
    }

    @Test
    fun `Throws TierListResultException if tier list result is null`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        assertThrows(TierListResultException::class.java) {
            viewModel.handleTierListResult(null)
        }
    }

    @Test
    fun `Successfully saves new update to the database`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        val saveErrorObserver = viewModel.saveErrorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        assertEquals(addedTierList, fakePaperRepository.savedTierList)
        saveErrorObserver.assertNoValue()
    }

    @Test
    fun `Shows error when unable to save new tier list to the database`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        fakePaperRepository.shouldSaveSuccessfully = false
        initViewModel()

        val saveErrorObserver = viewModel.saveErrorEvent.test()
        viewModel.handleTierListResult(addedTierList)
        advanceUntilIdle()

        assertEquals(addedTierList, fakePaperRepository.savedTierList)
        saveErrorObserver.awaitValue(Unit)
    }

    @Test
    fun `Notifies UI when tier list is updated`() = runTest {
        fakePaperRepository.fakeTierLists = tierLists
        initViewModel()

        val updatePreviewObserver = viewModel.updatePreviewEvent.test()
        val updatedTierListPosition = tierLists.indexOfFirst { it.id == updatedTierList.id }
        viewModel.handleTierListResult(updatedTierList)
        advanceUntilIdle()

        updatePreviewObserver.awaitValue(updatedTierListPosition)
    }
}