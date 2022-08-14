package me.khruslan.tierlistmaker.tests.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.state.ListState
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.TierStyle
import me.khruslan.tierlistmaker.dataproviders.viewmodels.DashboardViewModelDataProvider
import me.khruslan.tierlistmaker.dataproviders.viewmodels.DashboardViewModelDataProvider.UpdatedLists
import me.khruslan.tierlistmaker.dataproviders.viewmodels.DashboardViewModelDataProvider.ValidPositions
import me.khruslan.tierlistmaker.fakes.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.FakePaperRepository
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.assertAll
import me.khruslan.tierlistmaker.utils.assertEmpty
import me.khruslan.tierlistmaker.utils.assertNotEmpty
import me.khruslan.tierlistmaker.utils.awaitValue
import me.khruslan.tierlistmaker.viewmodels.DashboardViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import kotlin.test.assertFailsWith

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DashboardViewModelTest.StandardTest::class,
    DashboardViewModelTest.ParameterizedTierListNameTest::class,
    DashboardViewModelTest.ParametrizedValidTierListPositionTest::class,
    DashboardViewModelTest.ParameterizedInvalidTierListPositionTest::class,
    DashboardViewModelTest.ParameterizedAddedTierListTest::class,
    DashboardViewModelTest.ParameterizedUpdatedTierListTest::class
)
class DashboardViewModelTest {

    open class CoreTest {

        @get:Rule
        val instantTaskExecutorRule = InstantTaskExecutorRule()

        @get:Rule
        val coroutineTestRule = CoroutineTestRule()

        protected lateinit var fakePaperRepository: FakePaperRepository
        protected lateinit var viewModel: DashboardViewModel

        @Before
        fun initFakePaperRepository() {
            fakePaperRepository = FakePaperRepository()
        }

        protected fun TestScope.initViewModel() {
            viewModel = DashboardViewModel(
                paperRepository = fakePaperRepository,
                dispatcherProvider = FakeDispatcherProvider()
            )

            advanceUntilIdle()
        }
    }

    class StandardTest : CoreTest() {

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
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            viewModel.tierListPreviewsLiveData.awaitValue(
                DashboardViewModelDataProvider.previews.toMutableList()
            )
            viewModel.listStateLiveData.awaitValue(ListState.Filled)
        }

        @Test
        fun `Refreshes previews`() = runTest {
            initViewModel()
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            viewModel.refreshPreviews()

            viewModel.listStateLiveData.awaitValue(ListState.Loading)
            advanceUntilIdle()
            viewModel.tierListPreviewsLiveData.awaitValue(
                DashboardViewModelDataProvider.previews.toMutableList()
            )
            viewModel.listStateLiveData.awaitValue(ListState.Filled)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedTierListNameTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DashboardViewModelDataProvider.tierListNames
        }

        @Parameterized.Parameter
        lateinit var tierListName: String

        @Test
        fun `Creates new tier list`() = runTest {
            initViewModel()
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
    }

    @RunWith(Parameterized::class)
    class ParametrizedValidTierListPositionTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ValidPositions.data
        }

        @Parameterized.Parameter(ValidPositions.positionParam)
        lateinit var inputPosition: Integer

        @Parameterized.Parameter(ValidPositions.tierListParam)
        lateinit var expectedTierList: TierList

        @Test
        fun `Returns tier list by position`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()
            val actualTierList = viewModel.getTierListByPosition(inputPosition.toInt())

            assertEquals(expectedTierList, actualTierList)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedInvalidTierListPositionTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DashboardViewModelDataProvider.invalidPositions
        }

        @Parameterized.Parameter
        lateinit var position: Integer

        @Test
        fun `Throws error when getting list by incorrect position`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            assertFailsWith<IndexOutOfBoundsException> {
                viewModel.getTierListByPosition(position.toInt())
            }
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedAddedTierListTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DashboardViewModelDataProvider.addedLists
        }

        @Parameterized.Parameter
        lateinit var addedTierList: TierList

        @Test
        fun `Notifies UI when new tier list is added`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            val addPreviewObserver = viewModel.addPreviewEvent.test()
            viewModel.handleTierListResult(addedTierList)
            advanceUntilIdle()

            viewModel.listStateLiveData.awaitValue(ListState.Filled)
            addPreviewObserver.awaitValue(DashboardViewModelDataProvider.lists.size)
        }

        @Test
        fun `Successfully saves new update to the database`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            val saveErrorObserver = viewModel.saveErrorEvent.test()
            viewModel.handleTierListResult(addedTierList)
            advanceUntilIdle()

            assertEquals(addedTierList, fakePaperRepository.savedTierList)
            saveErrorObserver.assertNoValue()
        }

        @Test
        fun `Shows error when unable to save new tier list to the database`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            fakePaperRepository.shouldSaveSuccessfully = false
            initViewModel()

            val saveErrorObserver = viewModel.saveErrorEvent.test()
            viewModel.handleTierListResult(addedTierList)
            advanceUntilIdle()

            assertEquals(addedTierList, fakePaperRepository.savedTierList)
            saveErrorObserver.awaitValue(Unit)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedUpdatedTierListTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = UpdatedLists.data
        }

        @Parameterized.Parameter(UpdatedLists.positionParam)
        lateinit var position: Integer

        @Parameterized.Parameter(UpdatedLists.updatedListParam)
        lateinit var updatedTierList: TierList

        @Test
        fun `Notifies UI when tier list is updated`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            val updatePreviewObserver = viewModel.updatePreviewEvent.test()
            viewModel.handleTierListResult(updatedTierList)
            advanceUntilIdle()

            updatePreviewObserver.awaitValue(position.toInt())
        }

        @Test
        fun `Successfully saves new update to the database`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            initViewModel()

            val saveErrorObserver = viewModel.saveErrorEvent.test()
            viewModel.handleTierListResult(updatedTierList)
            advanceUntilIdle()

            assertEquals(updatedTierList, fakePaperRepository.savedTierList)
            saveErrorObserver.assertNoValue()
        }

        @Test
        fun `Shows error when unable to save new tier list to the database`() = runTest {
            fakePaperRepository.fakeTierLists = DashboardViewModelDataProvider.lists.toMutableList()
            fakePaperRepository.shouldSaveSuccessfully = false
            initViewModel()

            val saveErrorObserver = viewModel.saveErrorEvent.test()
            viewModel.handleTierListResult(updatedTierList)
            advanceUntilIdle()

            assertEquals(updatedTierList, fakePaperRepository.savedTierList)
            saveErrorObserver.awaitValue(Unit)
        }
    }
}