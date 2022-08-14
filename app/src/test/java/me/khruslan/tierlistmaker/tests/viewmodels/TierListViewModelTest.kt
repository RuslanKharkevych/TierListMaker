package me.khruslan.tierlistmaker.tests.viewmodels

import android.app.Application
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.jraska.livedata.test
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.drag.effects.*
import me.khruslan.tierlistmaker.data.state.LoadingProgress
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.AddNewTierEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.AddTargetEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.DropInCachedTargetEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.DropInTargetEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.DroppedImages
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.InsertImageEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.RemoveImageEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.RemoveTargetEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.SavedImages
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.UpdateTargetEvents
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.ZoomedInImageSizes
import me.khruslan.tierlistmaker.dataproviders.viewmodels.TierListViewModelDataProvider.ZoomedOutImageSizes
import me.khruslan.tierlistmaker.fakes.FakeFileManager
import me.khruslan.tierlistmaker.fakes.FakeTierListProcessor
import me.khruslan.tierlistmaker.fakes.FakeTierStyleProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.addAll
import me.khruslan.tierlistmaker.utils.assertSealedEquals
import me.khruslan.tierlistmaker.utils.awaitValue
import me.khruslan.tierlistmaker.utils.awaitValues
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.khruslan.tierlistmaker.viewmodels.TierListViewModel
import me.khruslan.tierlistmaker.work.SaveTierListArgsProvider
import me.khruslan.tierlistmaker.work.SaveTierListWorker
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

private const val KEY_TIER_LIST = "tierList"
private const val CONTEXT_EXTENSION_PACKAGE =
    "me.khruslan.tierlistmaker.utils.extensions.ContextExtensionKt"

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TierListViewModelTest.StandardTest::class,
    TierListViewModelTest.ParameterizedTierListTest::class,
    TierListViewModelTest.ParameterizedZoomedInImageSizeTest::class,
    TierListViewModelTest.ParameterizedZoomedOutImageSizeTest::class,
    TierListViewModelTest.ParameterizedRemoveImageEventsTest::class,
    TierListViewModelTest.ParameterizedAddTargetEventsTest::class,
    TierListViewModelTest.ParameterizedRemoveTargetEventsTest::class,
    TierListViewModelTest.ParameterizedUpdateTargetEventsTest::class,
    TierListViewModelTest.ParameterizedDroppedImagesTest::class,
    TierListViewModelTest.ParameterizedDropInTargetEventsTest::class,
    TierListViewModelTest.ParameterizedDropInCachedTargetEventsTest::class,
    TierListViewModelTest.ParameterizedInsertImageEventsTest::class,
    TierListViewModelTest.ParameterizedSavedImagesTest::class,
    TierListViewModelTest.ParameterizedAddNewTierEventsTest::class
)
class TierListViewModelTest {

    open class CoreTest {

        @get:Rule
        val instantTaskExecutorRule = InstantTaskExecutorRule()

        @get:Rule
        val coroutineTestRule = CoroutineTestRule()

        @MockK
        protected lateinit var mockApplication: Application

        private lateinit var savedStateHandle: SavedStateHandle
        protected lateinit var dragPocket: DragPocket
        protected lateinit var fakeFileManager: FakeFileManager
        protected lateinit var fakeTierListProcessor: FakeTierListProcessor
        protected lateinit var fakeTierStyleProvider: FakeTierStyleProvider
        protected lateinit var saveTierListArgsProvider: SaveTierListArgsProvider

        protected lateinit var viewModel: TierListViewModel

        @Before
        fun init() {
            MockKAnnotations.init(this)

            savedStateHandle = SavedStateHandle()
            dragPocket = DragPocket()
            fakeFileManager = FakeFileManager()
            fakeTierListProcessor = FakeTierListProcessor()
            fakeTierStyleProvider = FakeTierStyleProvider()
            saveTierListArgsProvider = SaveTierListArgsProvider()
        }

        protected fun initViewModel() {
            viewModel = TierListViewModel(
                application = mockApplication,
                savedStateHandle = savedStateHandle,
                dragPocket = dragPocket,
                fileManager = fakeFileManager,
                tierListProcessor = fakeTierListProcessor,
                tierStyleProvider = fakeTierStyleProvider,
                saveTierListArgsProvider = saveTierListArgsProvider
            )
        }

        protected fun initViewModelWithTierList(tierList: TierList) {
            savedStateHandle[KEY_TIER_LIST] = tierList
            initViewModel()
        }

        protected fun verifyProcessedDragEffects(vararg effects: DragEffect) {
            val processedEffects = fakeTierListProcessor.processedDragEffects
            assertEquals(effects.size, processedEffects.size)

            effects.forEachIndexed { index, effect ->
                assertSealedEquals(effect, processedEffects[index])
            }
        }
    }

    class StandardTest : CoreTest() {

        @Test
        fun `Throws error if SavedStateHandle doesn't contain tier list`() {
            assertFailsWith<IllegalArgumentException> {
                initViewModel()
            }
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedTierListTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListViewModelDataProvider.lists
        }

        @Parameterized.Parameter
        lateinit var tierList: TierList

        @Test
        fun `Sets tier list from SavedStateHandle on init`() {
            initViewModelWithTierList(tierList)

            assertEquals(tierList, viewModel.tierList)
            assertEquals(tierList, fakeTierListProcessor.initializedTierList)
        }

        @Test
        fun `Doesn't produce any events on drag target update if it remains empty`() {
            initViewModelWithTierList(tierList)
            viewModel.updateDragTarget(null)

            viewModel.tierListEvent.test().assertNoValue()
        }

        @Test
        fun `Doesn't produce any events on drag end if shadow is null`() {
            initViewModelWithTierList(tierList)
            viewModel.endDrag()

            viewModel.tierListEvent.test().assertNoValue()
        }

        @Test
        fun `Enqueues work to save tier list`() {
            mockkStatic(OneTimeWorkRequest::class, WorkManager::class)
            val mockWorkRequest: OneTimeWorkRequest = mockk()
            val mockWorkManager: WorkManager = mockk()
            every { OneTimeWorkRequest.from(SaveTierListWorker::class.java) } returns mockWorkRequest
            every { WorkManager.getInstance(mockApplication) } returns mockWorkManager
            every { mockWorkManager.enqueue(mockWorkRequest) } returns mockk()

            initViewModelWithTierList(tierList)
            viewModel.enqueueSaveTierListWork()

            assertEquals(tierList, saveTierListArgsProvider.tierList)
            verify { mockWorkManager.enqueue(mockWorkRequest) }
            unmockkStatic(OneTimeWorkRequest::class, WorkManager::class)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedZoomedInImageSizeTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ZoomedInImageSizes.data
        }

        @Parameterized.Parameter(ZoomedInImageSizes.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(ZoomedInImageSizes.displayWidthParam)
        lateinit var displayWidth: Integer

        @Parameterized.Parameter(ZoomedInImageSizes.imageSizeParam)
        lateinit var expectedImageSize: Integer

        @Test
        fun `Zooms in`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            mockkStatic(CONTEXT_EXTENSION_PACKAGE)
            every { mockApplication.displayWidthPixels } returns displayWidth.toInt()
            viewModel.zoomIn()

            tierListEventObserver.awaitValue(ImageSizeUpdated(expectedImageSize.toInt()))
            unmockkStatic(CONTEXT_EXTENSION_PACKAGE)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedZoomedOutImageSizeTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = ZoomedOutImageSizes.data
        }

        @Parameterized.Parameter(ZoomedOutImageSizes.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(ZoomedOutImageSizes.displayWidthParam)
        lateinit var displayWidth: Integer


        @Parameterized.Parameter(ZoomedOutImageSizes.imageSizeParam)
        lateinit var expectedImageSize: Integer

        @Test
        fun `Zooms out`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            mockkStatic(CONTEXT_EXTENSION_PACKAGE)
            every { mockApplication.displayWidthPixels } returns displayWidth.toInt()
            viewModel.zoomOut()

            tierListEventObserver.awaitValue(ImageSizeUpdated(expectedImageSize.toInt()))
            unmockkStatic(CONTEXT_EXTENSION_PACKAGE)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedRemoveImageEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = RemoveImageEvents.data
        }

        @Parameterized.Parameter(RemoveImageEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(RemoveImageEvents.imageDragDataParam)
        lateinit var imageDragData: ImageDragData

        @Parameterized.Parameter(RemoveImageEvents.dragEffectParam)
        lateinit var dragEffect: DragEffect

        @Parameterized.Parameter(RemoveImageEvents.tierListEventParam)
        lateinit var tierListEvent: TierListEvent

        @Test
        fun `Removes image and puts it in pocket when drag is started`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            fakeTierListProcessor.fakeEvents.addLast(tierListEvent)
            viewModel.startDrag(imageDragData)

            assertEquals(imageDragData, dragPocket.shadow)
            verifyProcessedDragEffects(dragEffect)
            tierListEventObserver.awaitValue(tierListEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedAddTargetEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = AddTargetEvents.data
        }

        @Parameterized.Parameter(AddTargetEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(AddTargetEvents.newTargetParam)
        lateinit var newTarget: DragData

        @Parameterized.Parameter(AddTargetEvents.highlightTargetEffectParam)
        lateinit var highlightTargetEffect: HighlightEffect

        @Parameterized.Parameter(AddTargetEvents.highlightTargetEventParam)
        lateinit var highlightTargetEvent: TierListEvent

        @Test
        fun `Highlights drag target`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            fakeTierListProcessor.fakeEvents.addLast(highlightTargetEvent)
            viewModel.updateDragTarget(newTarget)

            assertEquals(newTarget, dragPocket.target)
            verifyProcessedDragEffects(highlightTargetEffect)
            tierListEventObserver.awaitValue(highlightTargetEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedRemoveTargetEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = RemoveTargetEvents.data
        }

        @Parameterized.Parameter(RemoveTargetEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(RemoveTargetEvents.oldTargetParam)
        lateinit var oldTarget: DragData

        @Parameterized.Parameter(RemoveTargetEvents.removeTargetEffectParam)
        lateinit var removeTargetEffect: RemoveEffect

        @Parameterized.Parameter(RemoveTargetEvents.removeTargetEventParam)
        lateinit var removeTargetEvent: TierListEvent

        @Test
        fun `Removes drag target`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            dragPocket.target = oldTarget
            fakeTierListProcessor.fakeEvents.addLast(removeTargetEvent)
            viewModel.updateDragTarget(null)

            assertNull(dragPocket.target)
            verifyProcessedDragEffects(removeTargetEffect)
            assertEquals(1, fakeTierListProcessor.processedDragEffects.size)
            tierListEventObserver.awaitValue(removeTargetEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedUpdateTargetEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = UpdateTargetEvents.data
        }

        @Parameterized.Parameter(UpdateTargetEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(UpdateTargetEvents.oldTargetParam)
        lateinit var oldTarget: DragData

        @Parameterized.Parameter(UpdateTargetEvents.newTargetParam)
        lateinit var newTarget: DragData

        @Parameterized.Parameter(UpdateTargetEvents.removeTargetEffectParam)
        lateinit var removeTargetEffect: RemoveEffect

        @Parameterized.Parameter(UpdateTargetEvents.highlightTargetEffectParam)
        lateinit var highlightTargetEffect: HighlightEffect

        @Parameterized.Parameter(UpdateTargetEvents.removeTargetEventParam)
        lateinit var removeTargetEvent: TierListEvent

        @Parameterized.Parameter(UpdateTargetEvents.highlightTargetEventParam)
        lateinit var highlightTargetEvent: TierListEvent

        @Test
        fun `When drag target changes removes the old one and highlights the new one`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            dragPocket.target = oldTarget
            fakeTierListProcessor.fakeEvents.addAll(removeTargetEvent, highlightTargetEvent)
            viewModel.updateDragTarget(newTarget)

            assertEquals(newTarget, dragPocket.target)
            verifyProcessedDragEffects(removeTargetEffect, highlightTargetEffect)
            tierListEventObserver.awaitValues(removeTargetEvent, highlightTargetEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedDroppedImagesTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DroppedImages.data
        }

        @Parameterized.Parameter(DroppedImages.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(DroppedImages.droppedImageParam)
        lateinit var imageDragData: ImageDragData

        @Test
        fun `Doesn't drop image if unable to get drag target from pocket`() {
            initViewModelWithTierList(tierList)
            viewModel.dropImage(imageDragData)

            viewModel.tierListEvent.test().assertNoValue()
        }

        @Test
        fun `Throws exception when attempting to update target bin target`() {
            initViewModelWithTierList(tierList)
            dragPocket.target = TrashBinDragData

            assertFailsWith<IllegalArgumentException> {
                viewModel.dropImage(imageDragData)
            }
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedDropInTargetEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DropInTargetEvents.data
        }

        @Parameterized.Parameter(DropInTargetEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(DropInTargetEvents.droppedImageParam)
        lateinit var droppedImage: ImageDragData

        @Parameterized.Parameter(DropInTargetEvents.targetParam)
        lateinit var target: DragData

        @Parameterized.Parameter(DropInTargetEvents.updateTargetEffectParam)
        lateinit var updateTargetEffect: UpdateEffect

        @Parameterized.Parameter(DropInTargetEvents.updateTargetEventParam)
        lateinit var updateTargetEvent: TierListEvent

        @Test
        fun `Drops image in target`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            dragPocket.target = target
            fakeTierListProcessor.fakeEvents.addLast(updateTargetEvent)
            viewModel.dropImage(droppedImage)

            assertNull(dragPocket.target)
            verifyProcessedDragEffects(updateTargetEffect)
            tierListEventObserver.awaitValue(updateTargetEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedDropInCachedTargetEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = DropInCachedTargetEvents.data
        }

        @Parameterized.Parameter(DropInCachedTargetEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(DropInCachedTargetEvents.droppedImageParam)
        lateinit var droppedImage: ImageDragData

        @Parameterized.Parameter(DropInCachedTargetEvents.cachedTargetParam)
        lateinit var target: DragData

        @Parameterized.Parameter(DropInCachedTargetEvents.insertTargetEffectParam)
        lateinit var insertTargetEffect: InsertEffect

        @Parameterized.Parameter(DropInCachedTargetEvents.insertTargetEventParam)
        lateinit var insertTargetEvent: TierListEvent

        @Test
        fun `Drops image in cached target`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            dragPocket.cachedTarget = target
            fakeTierListProcessor.fakeEvents.addLast(insertTargetEvent)
            viewModel.dropImage(droppedImage)

            assertNull(dragPocket.cachedTarget)
            verifyProcessedDragEffects(insertTargetEffect)
            tierListEventObserver.awaitValue(insertTargetEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedInsertImageEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = InsertImageEvents.data
        }

        @Parameterized.Parameter(InsertImageEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(InsertImageEvents.shadowParam)
        lateinit var shadow: ImageDragData

        @Parameterized.Parameter(InsertImageEvents.insertImageEffectParam)
        lateinit var insertImageEffect: InsertEffect

        @Parameterized.Parameter(InsertImageEvents.insertImageEventParam)
        lateinit var insertImageEvent: TierListEvent

        @Test
        fun `Restores image once drag has ended`() {
            initViewModelWithTierList(tierList)
            val tierListEventObserver = viewModel.tierListEvent.test()
            dragPocket.shadow = shadow
            fakeTierListProcessor.fakeEvents.addLast(insertImageEvent)
            viewModel.endDrag()

            assertNull(dragPocket.shadow)
            verifyProcessedDragEffects(insertImageEffect)
            tierListEventObserver.awaitValue(insertImageEvent)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedSavedImagesTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = SavedImages.data
        }

        @Parameterized.Parameter(SavedImages.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(SavedImages.filePathsParam)
        lateinit var filePaths: List<String?>

        @Parameterized.Parameter(SavedImages.loadingProgressStatesParam)
        lateinit var loadingProgressStates: Array<LoadingProgress?>

        @Parameterized.Parameter(SavedImages.savedImagePayloadsParam)
        lateinit var savedImagePayloads: List<String>

        @Test
        fun `Saves images from image URIs`() = runTest {
            val imageUris = List(filePaths.size) { Uri.EMPTY }
            val files = filePaths.map { path ->
                mockk<File>()
                    .takeIf { path != null }
                    ?.also { file ->
                        every { file.path } returns path.orEmpty()
                    }
            }

            fakeTierStyleProvider.fakeStyles = tierList.tiers.map { it.style }
            fakeFileManager.fakeFiles.addAll(files)
            initViewModelWithTierList(tierList)
            advanceUntilIdle()
            val tierListEventObserver = viewModel.tierListEvent.test()
            val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
            viewModel.saveImages(imageUris)
            advanceUntilIdle()
            val actualSavedImagePayloads = tierList.backlogImages
                .map { it.payload }
                .take(savedImagePayloads.size)

            assertEquals(savedImagePayloads, actualSavedImagePayloads)
            tierListEventObserver.awaitValue(BacklogImagesAdded)
            loadingProgressObserver.awaitValues(*loadingProgressStates)
            assertEquals(imageUris, fakeFileManager.processedUris)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedAddNewTierEventsTest : CoreTest() {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = AddNewTierEvents.data
        }

        @Parameterized.Parameter(AddNewTierEvents.tierListParam)
        lateinit var tierList: TierList

        @Parameterized.Parameter(AddNewTierEvents.newTierStylesParam)
        lateinit var newTierStyles: Array<TierStyle>

        @Parameterized.Parameter(AddNewTierEvents.addNewTierEventsParam)
        lateinit var addNewTierEvents: Array<TierListEvent>

        @After
        fun tearDown() {
            tierList.tiers.removeLast()
        }

        @Test
        fun `Adds new tier and updates tier styles`() = runTest {
            fakeTierStyleProvider.fakeStyles = tierList.tiers.map { it.style }
            initViewModelWithTierList(tierList)
            advanceUntilIdle()

            fakeTierStyleProvider.fakeStyles = newTierStyles.toList()
            val tierListEventObserver = viewModel.tierListEvent.test()
            viewModel.addNewTier()
            advanceUntilIdle()

            val actualEvents = tierListEventObserver.valueHistory()
            addNewTierEvents.forEachIndexed { index, expectedEvent ->
                assertSealedEquals(expectedEvent, actualEvents[index])
            }
            assertEquals(newTierStyles.size, fakeTierStyleProvider.processedSize)
            assertEquals(newTierStyles.toList(), tierList.tiers.map { it.style })
        }
    }
}