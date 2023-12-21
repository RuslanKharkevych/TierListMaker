package me.khruslan.tierlistmaker.tests.ui.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.effects.HighlightInTier
import me.khruslan.tierlistmaker.data.models.drag.effects.HighlightLastInTier
import me.khruslan.tierlistmaker.data.models.drag.effects.InsertToBacklog
import me.khruslan.tierlistmaker.data.models.drag.effects.InsertToTier
import me.khruslan.tierlistmaker.data.models.drag.effects.RemoveFromBacklog
import me.khruslan.tierlistmaker.data.models.drag.effects.RemoveFromTier
import me.khruslan.tierlistmaker.data.models.drag.effects.UpdateInTier
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogDataChanged
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogImagesAdded
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogItemInserted
import me.khruslan.tierlistmaker.data.models.tierlist.ImageSizeUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierInserted
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierListExportError
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToShare
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToView
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.fakes.data.providers.file.FakeFileManager
import me.khruslan.tierlistmaker.fakes.presentation.utils.tierlist.FakeTierListBitmapGenerator
import me.khruslan.tierlistmaker.fakes.data.providers.tierlist.FakeTierListProcessor
import me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier.FakeTierStyleProvider
import me.khruslan.tierlistmaker.fakes.data.providers.drag.FakeDragPocket
import me.khruslan.tierlistmaker.fakes.utils.performance.NoOpPerformanceService
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.presentation.models.LoadingProgress
import me.khruslan.tierlistmaker.presentation.viewmodels.TierListViewModel
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.awaitValue
import me.khruslan.tierlistmaker.utils.awaitValues
import me.khruslan.tierlistmaker.util.displayWidthPixels
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

@ExperimentalCoroutinesApi
class TierListViewModelTest {

    private companion object {
        private const val KEY_TIER_LIST = "tierList"
        private const val EXTENSIONS_PACKAGE = "me.khruslan.tierlistmaker.util.ExtensionsKt"
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var mockApplication: Application

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var fakeDragPocket: FakeDragPocket
    private lateinit var fakeFileManager: FakeFileManager
    private lateinit var fakeTierListProcessor: FakeTierListProcessor
    private lateinit var fakeTierStyleProvider: FakeTierStyleProvider
    private lateinit var fakeTierListBitmapGenerator: FakeTierListBitmapGenerator

    private lateinit var viewModel: TierListViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)

        savedStateHandle = SavedStateHandle()
        fakeDragPocket = FakeDragPocket()
        fakeFileManager = FakeFileManager()
        fakeTierListProcessor = FakeTierListProcessor()
        fakeTierStyleProvider = FakeTierStyleProvider()
        fakeTierListBitmapGenerator = FakeTierListBitmapGenerator()
    }

    private fun initViewModel() {
        viewModel = TierListViewModel(
            application = mockApplication,
            savedStateHandle = savedStateHandle,
            dragPocket = fakeDragPocket,
            fileManager = fakeFileManager,
            tierListProcessor = fakeTierListProcessor,
            tierStyleProvider = fakeTierStyleProvider,
            tierListBitmapGenerator = fakeTierListBitmapGenerator,
            performanceService = NoOpPerformanceService()
        )
    }

    private fun initViewModelWithTierList() {
        savedStateHandle[KEY_TIER_LIST] = tierList
        initViewModel()
    }

    private val tierList = TierList(
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

    private val dragTarget
        get() = ImageDragData(
            image = StorageImage(
                id = "c042daa2-348f-48ac-8b2d-5f04f9eeda14",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/0079150423601.jpeg"
            ),
            tierPosition = 1,
            itemPosition = 1
        )

    private val dragShadow
        get() = ImageDragData(
            image = StorageImage(
                id = "59b5e3bc-a89b-48fc-b96e-d43ccdc92223",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/0517968161857.jpeg"
            ),
            tierPosition = BACKLOG_TIER_POSITION,
            itemPosition = 2
        )

    @Test
    fun `Throws error if SavedStateHandle doesn't contain tier list`() {
        assertThrows(IllegalStateException::class.java) {
            initViewModel()
        }
    }

    @Test
    fun `Sets tier list from SavedStateHandle on init`() {
        initViewModelWithTierList()

        assertEquals(tierList, viewModel.tierList)
        assertEquals(tierList, fakeTierListProcessor.initializedTierList)
    }

    @Test
    fun `Doesn't produce any events on drag target update if it remains empty`() {
        initViewModelWithTierList()
        viewModel.updateDragTarget(null)

        viewModel.tierListEvent.test().assertNoValue()
    }

    @Test
    fun `Doesn't produce any events on drag end if shadow is null`() {
        initViewModelWithTierList()
        viewModel.restoreReleasedImage()

        viewModel.tierListEvent.test().assertNoValue()
    }

    @Test
    fun `Zooms in`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val displayWidth = 720
        val expectedImageSize = 240
        mockkStatic(EXTENSIONS_PACKAGE)
        every { mockApplication.displayWidthPixels } returns displayWidth
        viewModel.zoomIn()

        tierListEventObserver.awaitValue(ImageSizeUpdated(expectedImageSize))
        unmockkStatic(EXTENSIONS_PACKAGE)
    }

    @Test
    fun `Zooms out`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val displayWidth = 720
        val expectedImageSize = 144
        mockkStatic(EXTENSIONS_PACKAGE)
        every { mockApplication.displayWidthPixels } returns displayWidth
        viewModel.zoomOut()

        tierListEventObserver.awaitValue(ImageSizeUpdated(expectedImageSize))
        unmockkStatic(EXTENSIONS_PACKAGE)
    }

    @Test
    fun `Removes image and puts it in pocket when drag is started`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = BacklogDataChanged
        val imageDragData = ImageDragData(
            image = tierList.backlogImages[1],
            itemPosition = 1,
            tierPosition = BACKLOG_TIER_POSITION
        )
        val dragEffect = RemoveFromBacklog(itemPosition = 1)
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.startDrag(imageDragData)

        assertEquals(imageDragData, fakeDragPocket.shadow)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `Highlights drag target`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = TierChanged(tierPosition = 0)
        val dragTarget = ImageDragData(
            image = tierList.tiers[0].images[0],
            itemPosition = 0,
            tierPosition = 0
        )
        val dragEffect = HighlightInTier(tierPosition = 0, itemPosition = 0)
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.updateDragTarget(dragTarget)

        assertEquals(dragTarget, fakeDragPocket.target)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `Removes drag target`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = TierChanged(tierPosition = 1)
        val dragEffect = RemoveFromTier(tierPosition = 1, itemPosition = 1)

        fakeDragPocket.target = dragTarget
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.updateDragTarget(null)

        assertNull(fakeDragPocket.target)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `When drag target changes removes the old one and highlights the new one`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val removeTargetEvent = TierChanged(tierPosition = 1)
        val highlightTargetEvent = TierChanged(tierPosition = 0)
        val newDragTarget = TierDragData(tierPosition = 0)
        val removeTargetEffect = RemoveFromTier(tierPosition = 1, itemPosition = 1)
        val highlightTargetEffect = HighlightLastInTier(tierPosition = 0)

        fakeDragPocket.target = dragTarget
        fakeTierListProcessor.events[removeTargetEffect] = removeTargetEvent
        fakeTierListProcessor.events[highlightTargetEffect] = highlightTargetEvent
        viewModel.updateDragTarget(newDragTarget)

        assertEquals(newDragTarget, fakeDragPocket.target)
        tierListEventObserver.awaitValues(removeTargetEvent, highlightTargetEvent)
    }

    @Test
    fun `Doesn't drop image if unable to get drag target from pocket`() {
        initViewModelWithTierList()
        viewModel.dropImage(dragShadow)

        viewModel.tierListEvent.test().assertNoValue()
    }

    @Test
    fun `Drops image in target`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = TierChanged(tierPosition = 1)
        val dragEffect = UpdateInTier(data = dragTarget.copy(image = dragShadow.image))

        fakeDragPocket.target = dragTarget
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.dropImage(dragShadow)

        assertNull(fakeDragPocket.target)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `Drops image in cached target`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = TierChanged(tierPosition = 1)
        val dragEffect = InsertToTier(data = dragTarget.copy(image = dragShadow.image))

        fakeDragPocket.target = dragTarget
        fakeDragPocket.target = null
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.dropImage(dragShadow)

        assertNull(fakeDragPocket.cachedTarget)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `Restores released image`() {
        initViewModelWithTierList()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val tierListEvent = BacklogItemInserted(itemPosition = 2)
        val dragEffect = InsertToBacklog(data = dragShadow)

        fakeDragPocket.shadow = dragShadow
        fakeTierListProcessor.events[dragEffect] = tierListEvent
        viewModel.restoreReleasedImage()

        assertNull(fakeDragPocket.shadow)
        tierListEventObserver.awaitValue(tierListEvent)
    }

    @Test
    fun `Saves images from image URIs`() = runTest {
        val filePaths = listOf(
            "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/7859234484977.jpeg",
            null,
            "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/3575526202683.jpeg"
        )
        val imageUris = List(filePaths.size) { mockk<Uri>() }
        val files = filePaths.map { path ->
            mockk<File>()
                .takeIf { path != null }
                ?.also { file ->
                    every { file.path } returns path.orEmpty()
                }
        }
        val expectedLoadingProgressStates = arrayOf(
            LoadingProgress.Determinate(currentItem = 1, totalItems = 3),
            LoadingProgress.Determinate(currentItem = 2, totalItems = 3),
            LoadingProgress.Determinate(currentItem = 3, totalItems = 3),
            null
        )
        val expectedSavedImagePayloads = listOf(
            "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/7859234484977.jpeg",
            R.drawable.ic_broken_image.toString(),
            "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/3575526202683.jpeg"
        )

        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        fakeFileManager.files = imageUris.zip(files).toMap()
        initViewModelWithTierList()
        advanceUntilIdle()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
        viewModel.saveImages(imageUris)
        advanceUntilIdle()
        val actualSavedImagePayloads = tierList.backlogImages
            .map { it.payload }
            .take(expectedSavedImagePayloads.size)

        assertEquals(expectedSavedImagePayloads, actualSavedImagePayloads)
        tierListEventObserver.awaitValue(BacklogImagesAdded)
        loadingProgressObserver.awaitValues(*expectedLoadingProgressStates)
    }

    @Test
    fun `Adds new tier and updates tier styles`() = runTest {
        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        initViewModelWithTierList()
        advanceUntilIdle()

        val tierListEventObserver = viewModel.tierListEvent.test()
        val newTierStyles = listOf(
            TierStyle(
                title = "S",
                color = Color.RED
            ),
            TierStyle(
                title = "A",
                color = Color.YELLOW
            ),
            TierStyle(
                title = "B",
                color = Color.GREEN
            )
        )
        val expectedEvents = listOf(TierInserted(tierPosition = 2), TierListChanged)
        fakeTierStyleProvider.styles = newTierStyles.toList()
        viewModel.addNewTier()
        advanceUntilIdle()

        val actualEvents = tierListEventObserver.valueHistory()
        assertEquals(expectedEvents.size, actualEvents.size)
        expectedEvents.forEachIndexed { index, expectedEvent ->
            assertEquals(expectedEvent, actualEvents[index])
        }
        assertEquals(newTierStyles.toList(), tierList.tiers.map { it.style })
    }

    @Test
    fun `On share tier list error produces export error event`() = runTest {
        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        initViewModelWithTierList()
        advanceUntilIdle()

        val bitmap: Bitmap = mockk()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
        val tierListEvent = TierListExportError(R.string.snackbar_msg_share_file_error)
        val loadingProgressEvents = arrayOf(LoadingProgress.Indeterminate, null)
        fakeTierListBitmapGenerator.bitmaps = mapOf(tierList to bitmap)
        viewModel.shareTierList()
        advanceUntilIdle()

        tierListEventObserver.awaitValue(tierListEvent)
        loadingProgressObserver.awaitValues(*loadingProgressEvents)
    }

    @Test
    fun `On share tier list success produces ready to share event`() = runTest {
        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        initViewModelWithTierList()
        advanceUntilIdle()

        val bitmap: Bitmap = mockk()
        val uri: Uri = mockk()
        val fileName = "${tierList.title}${FileManager.FILENAME_EXTENSION_JPEG}"
        val tierListEventObserver = viewModel.tierListEvent.test()
        val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
        val tierListEvent = TierListReadyToShare(uri)
        val loadingProgressEvents = arrayOf(LoadingProgress.Indeterminate, null)
        fakeTierListBitmapGenerator.bitmaps = mapOf(tierList to bitmap)
        fakeFileManager.contentUris = mapOf(bitmap to fileName to uri)
        viewModel.shareTierList()
        advanceUntilIdle()

        tierListEventObserver.awaitValue(tierListEvent)
        loadingProgressObserver.awaitValues(*loadingProgressEvents)
    }

    @Test
    fun `On view tier list error produces export error event`() = runTest {
        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        initViewModelWithTierList()
        advanceUntilIdle()

        val bitmap: Bitmap = mockk()
        val tierListEventObserver = viewModel.tierListEvent.test()
        val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
        val tierListEvent = TierListExportError(R.string.snackbar_msg_view_file_error)
        val loadingProgressEvents = arrayOf(LoadingProgress.Indeterminate, null)
        fakeTierListBitmapGenerator.bitmaps = mapOf(tierList to bitmap)
        viewModel.viewTierList()
        advanceUntilIdle()

        tierListEventObserver.awaitValue(tierListEvent)
        loadingProgressObserver.awaitValues(*loadingProgressEvents)
    }

    @Test
    fun `On view tier list success produces ready to share event`() = runTest {
        fakeTierStyleProvider.styles = tierList.tiers.map { it.style }
        initViewModelWithTierList()
        advanceUntilIdle()

        val bitmap: Bitmap = mockk()
        val uri: Uri = mockk()
        val fileName = "${tierList.title}${FileManager.FILENAME_EXTENSION_JPEG}"
        val tierListEventObserver = viewModel.tierListEvent.test()
        val loadingProgressObserver = viewModel.loadingProgressLiveData.test()
        val tierListEvent = TierListReadyToView(uri)
        val loadingProgressEvents = arrayOf(LoadingProgress.Indeterminate, null)
        fakeTierListBitmapGenerator.bitmaps = mapOf(tierList to bitmap)
        fakeFileManager.contentUris = mapOf(bitmap to fileName to uri)
        viewModel.viewTierList()
        advanceUntilIdle()

        tierListEventObserver.awaitValue(tierListEvent)
        loadingProgressObserver.awaitValues(*loadingProgressEvents)
    }
}