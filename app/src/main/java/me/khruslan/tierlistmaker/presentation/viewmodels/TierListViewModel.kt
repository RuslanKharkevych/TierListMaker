package me.khruslan.tierlistmaker.presentation.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.drag.effects.HighlightEffect
import me.khruslan.tierlistmaker.data.models.drag.effects.InsertEffect
import me.khruslan.tierlistmaker.data.models.drag.effects.RemoveEffect
import me.khruslan.tierlistmaker.data.models.drag.effects.UpdateEffect
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogImagesAdded
import me.khruslan.tierlistmaker.data.models.tierlist.ImageSizeUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierInserted
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.models.tierlist.TierListExportError
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToShare
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToView
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGenerator
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierStyleProvider
import me.khruslan.tierlistmaker.presentation.models.LoadingProgress
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListFragment
import me.khruslan.tierlistmaker.util.displayWidthPixels
import me.khruslan.tierlistmaker.data.providers.drag.DragPocket
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.SaveImagesTrace
import timber.log.Timber
import java.io.File
import javax.inject.Inject

/**
 * View model for [TierListFragment].
 *
 * The view model operates on a tier list object received from the saved state handle. Handles
 * drag effects and sends tier list events that allow observers to reflect UI changes.
 *
 * @property savedStateHandle Provides navigation arguments.
 * @property dragPocket Keeps drag data in memory.
 * @property fileManager Saves image files.
 * @property tierListProcessor Processes drag effects.
 * @property tierStyleProvider Provides tier styles.
 * @property performanceService Tracks performance traces.
 * @param application Provides access to global resources.
 * @constructor Creates view model with all dependencies.
 */
@HiltViewModel
class TierListViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val dragPocket: DragPocket,
    private val fileManager: FileManager,
    private val tierListProcessor: TierListProcessor,
    private val tierStyleProvider: TierStyleProvider,
    private val tierListBitmapGenerator: TierListBitmapGenerator,
    private val performanceService: PerformanceService
) : AndroidViewModel(application) {

    /**
     * Navigation argument keys.
     *
     * These keys must be passed as arguments when navigating to the [TierListFragment]. Inside the
     * view model, the arguments can be obtained from [savedStateHandle].
     */
    private companion object NavArgKeys {

        /**
         * Key of the [TierList] argument.
         */
        private const val KEY_TIER_LIST = "tierList"

        /**
         * Key of the [TierListHintStep.name] argument.
         */
        private const val KEY_HINT_STEP_NAME = "hintStepName"
    }

    /**
     * Initial tier list instance obtained from navigation arguments.
     *
     * @throws [IllegalStateException] If argument was not found.
     */
    val tierList: TierList = savedStateHandle[KEY_TIER_LIST]
        ?: throw IllegalStateException("$savedStateHandle doesn't contain key: $KEY_TIER_LIST")

    /**
     * Updates styles of all tiers in the tier list.
     *
     * Make sure to cancel the old job whenever the new one is launched to avoid race condition
     * bugs.
     */
    private var updateTierListStylesJob: Job? = null

    /**
     * A mutable reference to [tierListEvent].
     */
    private val _tierListEvent by lazy { LiveEvent<TierListEvent>() }

    /**
     * A mutable reference to [hintEvent].
     */
    private val _hintEvent by lazy {
        LiveEvent<TierListHintStep>(LiveEventConfig.PreferFirstObserver)
    }

    /**
     * A mutable reference to [loadingProgressLiveData].
     */
    private val _loadingProgressLiveData by lazy { MutableLiveData<LoadingProgress?>() }

    /**
     * Live data that notifies observers about the tier list events.
     */
    val tierListEvent: LiveData<TierListEvent> get() = _tierListEvent

    /**
     * Live data that notifies observers about the tier list hint step to show.
     */
    val hintEvent: LiveData<TierListHintStep> get() = _hintEvent

    /**
     * Live data that notifies observers about the progress of loading image files or creating an
     * image file of the tier list.
     *
     * If the value is null, then no loading is happening at the moment.
     */
    val loadingProgressLiveData: LiveData<LoadingProgress?> get() = _loadingProgressLiveData

    /**
     * Width of the device screen in pixels.
     *
     * Obtained from the application resources. Used to calculate image size.
     */
    private val displayWidth by lazy {
        getApplication<Application>().displayWidthPixels
    }

    /**
     * Size of the tier list image.
     *
     * Calculated based on the current [TierList.zoomValue].
     */
    val imageSize get() = displayWidth / tierList.zoomValue

    init {
        Timber.i("TierListViewModel initialized")
        initTierList()
        handleHint()
    }

    /**
     * Logs the onCleared lifecycle event.
     *
     * Called when this view model is no longer used and will be destroyed.
     */
    override fun onCleared() {
        Timber.i("TierListViewModel cleared")
    }

    /**
     * Decrements [TierList.zoomValue].
     *
     * Updates [tierListEvent] with [ImageSizeUpdated].
     */
    fun zoomIn() {
        tierList.zoomValue--
        _tierListEvent.value = ImageSizeUpdated(imageSize)
        Timber.i("Zoom value decreased to ${tierList.zoomValue}")
    }

    /**
     * Increments [TierList.zoomValue].
     *
     * Updates [tierListEvent] with [ImageSizeUpdated].
     */
    fun zoomOut() {
        tierList.zoomValue++
        _tierListEvent.value = ImageSizeUpdated(imageSize)
        Timber.i("Zoom value increased to ${tierList.zoomValue}")
    }

    /**
     * Starts drag.
     *
     * Saves [dragData] in the [dragPocket] and notifies UI that it was removed.
     *
     * @param dragData Drag data that received the drag event.
     */
    fun startDrag(dragData: ImageDragData) {
        dragPocket.shadow = dragData
        processDragEffect(RemoveEffect.create(dragData))
    }

    /**
     * Updates the drag target.
     *
     * - Pops old target from [dragPocket].
     * - Saves [newTarget] in the [dragPocket].
     * - Notifies UI about the updates.
     *
     * @param newTarget Drag data of the new target or null if target was removed.
     */
    fun updateDragTarget(newTarget: DragData?) {
        val oldTarget = dragPocket.target
        if (oldTarget != null) processDragEffect(RemoveEffect.create(oldTarget))

        dragPocket.target = newTarget
        if (newTarget != null) processDragEffect(HighlightEffect.create(newTarget))
    }

    /**
     * Drops the image into a target.
     *
     * Pops [DragPocket.target] (or [DragPocket.cachedTarget] if necessary) and notifies the UI that
     * it should be updated with [dragData]. If cached target is not found in the pocket, returns
     * without any action.
     *
     * @param dragData Drag data of the dropped image.
     */
    fun dropImage(dragData: ImageDragData) {
        val target = dragPocket.target

        if (target != null) {
            processDragEffect(UpdateEffect.create(dragData, target))
        } else {
            val cachedTarget = dragPocket.cachedTarget ?: return
            processDragEffect(InsertEffect.create(dragData, cachedTarget))
        }
    }

    /**
     * Restores released image from the "pocket".
     *
     * It can be called to either end the drag without dropping, or put back image. Pops
     * [DragPocket.shadow] and notified the UI that it should be restored.
     */
    fun restoreReleasedImage() {
        val shadow = dragPocket.shadow ?: return
        processDragEffect(InsertEffect.create(shadow))
    }

    /**
     * Saves images to the local storage.
     *
     * Inserts saved images at the start of the backlog and notifies UI about the updates. Traced
     * with [SaveImagesTrace].
     *
     * @param imageUris URIs of the images that should be saved.
     */
    fun saveImages(imageUris: List<Uri>) {
        viewModelScope.launch {
            val trace = performanceService.startTrace(SaveImagesTrace.NAME)
            val images = imageUris.mapIndexed { index, uri ->
                val file = fileManager.createImageFileFromUri(uri)
                createImage(file).also {
                    _loadingProgressLiveData.postValue(
                        LoadingProgress.Determinate(
                            currentItem = index + 1,
                            totalItems = imageUris.size
                        )
                    )
                }
            }

            trace.putMetric(SaveImagesTrace.METRIC_COUNT, images.size)
            trace.stop()
            insertImagesToBacklog(images)
            _loadingProgressLiveData.postValue(null)
        }
    }

    /**
     * Inserts a new empty tier at the end.
     *
     * Updates [tierListEvent] with [TierInserted]. Cancels current [updateTierListStylesJob] and
     * starts a new job to update style of all tiers.
     */
    fun addNewTier() {
        Timber.i("Adding new tier")
        updateTierListStylesJob?.cancel()
        tierList.tiers += Tier()
        _tierListEvent.value = TierInserted(tierList.tiers.lastIndex)
        updateTierStyles()
        Timber.i("Added new tier. Updated tier list: $tierList")
    }

    /**
     * Update styles of all tiers.
     *
     * Launches and assigns a job created by [launchUpdateTierStylesJob] to
     * [updateTierListStylesJob].
     */
    fun updateTierStyles() {
        updateTierListStylesJob = launchUpdateTierStylesJob()
    }

    /**
     * Runs initial configurations of the tier list.
     *
     * Initializes [tierList] in [tierListProcessor] and updates tier styles. This function must
     * be called from the view model's initialization block.
     */
    private fun initTierList() {
        tierListProcessor.setTierList(tierList)
        updateTierStyles()
    }

    /**
     * Produces [hintEvent] if [KEY_HINT_STEP_NAME] is found in navigation arguments.
     */
    private fun handleHint() {
        val hintStepName: String = savedStateHandle[KEY_HINT_STEP_NAME] ?: return
        _hintEvent.value = TierListHintStep.valueOf(hintStepName)
    }

    /**
     * Creates image from file.
     *
     * Normally creates [StorageImage], bu in case file is null, creates "broken" [ResourceImage].
     *
     * @param file Image file or null.
     * @return Created image.
     */
    private fun createImage(file: File?): Image {
        return if (file != null) {
            StorageImage(file)
        } else {
            ResourceImage(resId = R.drawable.ic_broken_image)
        }
    }

    /**
     * Creates and starts a job that updates styles of all tiers.
     *
     * On job completion updates [tierListEvent] with [TierListChanged].
     *
     * @return Created job.
     */
    private fun launchUpdateTierStylesJob(): Job {
        return viewModelScope.launch {
            val styles = tierStyleProvider.getTierStyles(tierList.tiers.size)
            tierList.tiers.forEachIndexed { index, tier ->
                tier.style = styles[index]
            }
            _tierListEvent.postValue(TierListChanged)
            Timber.i("Updated tier styles: $styles")
        }
    }

    /**
     * Converts drag effect to the tier list event.
     *
     * Delegates handling logic to [tierListProcessor]. Triggers [tierListEvent] update.
     *
     * @param effect Drag effect to process.
     */
    private fun processDragEffect(effect: DragEffect) {
        _tierListEvent.value = tierListProcessor.processDragEffect(effect)
    }

    /**
     * Handles share tier list action.
     *
     * Attempts to save tier list to a file. On success updates [tierListEvent] with
     * [TierListReadyToShare], on failure - with [TierListExportError].
     */
    fun shareTierList() {
        viewModelScope.launch {
            val uri = saveTierListToFile()
            _tierListEvent.value = if (uri != null) {
                TierListReadyToShare(uri)
            } else {
                TierListExportError(R.string.snackbar_msg_share_file_error)
            }
        }
    }

    /**
     * Handles view tier list action.
     *
     * Attempts to save tier list to a file. On success updates [tierListEvent] with
     * [TierListReadyToView], on failure - with [TierListExportError].
     */
    fun viewTierList() {
        viewModelScope.launch {
            val uri = saveTierListToFile()
            _tierListEvent.value = if (uri != null) {
                TierListReadyToView(uri)
            } else {
                TierListExportError(R.string.snackbar_msg_view_file_error)
            }
        }
    }

    /**
     * Generates bitmap from tier list and saves it to a file.
     *
     * The file name is taken from the tier list title. Updates [loadingProgressLiveData] with
     * indeterminate loading progress.
     *
     * @return Content URI of the created file or null in case of error.
     */
    private suspend fun saveTierListToFile(): Uri? {
        _loadingProgressLiveData.value = LoadingProgress.Indeterminate
        val bitmap = tierListBitmapGenerator.generate(tierList)
        val fileName = "${tierList.title}${FileManager.FILENAME_EXTENSION_JPEG}"
        val uri = fileManager.provideContentUriFromBitmap(bitmap, fileName)
        _loadingProgressLiveData.value = null
        return uri
    }

    /**
     * Inserts images at the start of the backlog.
     *
     * If the list of images is empty, completes without any action. Updates [tierListEvent] with
     * [BacklogImagesAdded].
     *
     * @param images Images to insert.
     */
    fun insertImagesToBacklog(images: List<Image>) {
        if (images.isEmpty()) return
        Timber.i("Inserting images to backlog: $images")
        tierList.backlogImages.addAll(0, images)
        _tierListEvent.value = BacklogImagesAdded
    }
}