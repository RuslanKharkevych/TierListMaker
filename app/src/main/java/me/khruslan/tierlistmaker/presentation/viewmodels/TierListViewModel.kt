package me.khruslan.tierlistmaker.presentation.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
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
import me.khruslan.tierlistmaker.util.performace.PerformanceService
import me.khruslan.tierlistmaker.util.performace.SaveImagesTrace
import timber.log.Timber
import java.io.File
import javax.inject.Inject

/**
 * [ViewModel] for [TierListFragment].
 *
 * @property savedStateHandle handle for saving [TierList].
 * @property dragPocket pocket for the [DragData].
 * @property fileManager manager for saving image files.
 * @property tierListProcessor processor of drag effects.
 * @property tierStyleProvider provider of tier styles.
 * @property performanceService service that starts performance traces.
 * @param application [Application] instance.
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
     * Companion object of [TierListViewModel] used for storing [SavedStateHandle] keys.
     */
    private companion object {
        private const val KEY_TIER_LIST = "tierList"
    }

    /**
     * Initial [TierList] instance obtained from navigation arguments.
     */
    val tierList: TierList = savedStateHandle[KEY_TIER_LIST]
        ?: throw IllegalStateException("$savedStateHandle doesn't contain key: $KEY_TIER_LIST")

    /**
     * [Job] that updates styles of all tiers in a [tierList].
     */
    private var updateTierListStylesJob: Job? = null

    private val _tierListEvent by lazy { LiveEvent<TierListEvent>() }
    private val _loadingProgressLiveData by lazy { MutableLiveData<LoadingProgress?>() }

    /**
     * [LiveData] that notifies observers about the tier list events.
     */
    val tierListEvent: LiveData<TierListEvent> get() = _tierListEvent

    /**
     * [LiveData] that notifies observers about the progress of loading image files or creating
     * an image file of the tier list.
     *
     * If the value is **null**, then no loading is happening at the moment.
     */
    val loadingProgressLiveData: LiveData<LoadingProgress?> get() = _loadingProgressLiveData

    /**
     * Width of the device screen in pixels.
     */
    private val displayWidth by lazy {
        getApplication<Application>().displayWidthPixels
    }

    /**
     * Size of the tier list image. Calculated based on the current [TierList.zoomValue].
     */
    val imageSize get() = displayWidth / tierList.zoomValue

    init {
        Timber.i("TierListViewModel initialized")
        tierListProcessor.setTierList(tierList)
        updateTierStyles()
    }

    override fun onCleared() {
        Timber.i("TierListViewModel cleared")
    }

    /**
     * Reduces [TierList.zoomValue] by one and notifies UI.
     */
    fun zoomIn() {
        tierList.zoomValue--
        _tierListEvent.value = ImageSizeUpdated(imageSize)
        Timber.i("Zoom value decreased to ${tierList.zoomValue}")
    }

    /**
     * Increases [TierList.zoomValue] by one and notifies UI.
     */
    fun zoomOut() {
        tierList.zoomValue++
        _tierListEvent.value = ImageSizeUpdated(imageSize)
        Timber.i("Zoom value increased to ${tierList.zoomValue}")
    }

    /**
     * Starts drag. Saves [dragData] in the [dragPocket] and notifies UI that it was removed.
     *
     * @param dragData drag data that received the drag event.
     */
    fun startDrag(dragData: ImageDragData) {
        dragPocket.shadow = dragData
        processDragEffect(RemoveEffect.create(dragData))
    }

    /**
     * Updates the drag target.
     * - Pops old target from [dragPocket].
     * - Saves [newTarget] in the [dragPocket].
     * - Notifies UI about the updates.
     *
     * @param newTarget drag data of the new target or **null** if target was removed.
     */
    fun updateDragTarget(newTarget: DragData?) {
        val oldTarget = dragPocket.target
        if (oldTarget != null) processDragEffect(RemoveEffect.create(oldTarget))

        dragPocket.target = newTarget
        if (newTarget != null) processDragEffect(HighlightEffect.create(newTarget))
    }

    /**
     * Drops the image. Pops [DragPocket.target] (or [DragPocket.cachedTarget] if necessary) and
     * notifies the UI that it should be updated with [dragData].
     *
     * @param dragData drag data of the dropped image.
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
     * Restores released image. It can be called to either end the drag without dropping, or put
     * back image. Pops [DragPocket.shadow] and notified the UI that it should be restored.
     */
    fun restoreReleasedImage() {
        val shadow = dragPocket.shadow ?: return
        processDragEffect(InsertEffect.create(shadow))
    }

    /**
     * Saves images to the local storage, adds them to the [TierList.backlogImages]
     * and notifies UI about the updates.
     *
     * @param imageUris list of [Uri] of the images that should be saved.
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
     * Creates image from [file]. If [file] is **null**, creates "broken" image.
     *
     * @param file image file or null.
     * @return Created [Image].
     */
    private fun createImage(file: File?): Image {
        return if (file != null) {
            StorageImage(file)
        } else {
            ResourceImage(resId = R.drawable.ic_broken_image)
        }
    }

    /**
     * Adds empty [Tier] and notifies the UI that it was inserted. Cancels current
     * [updateTierListStylesJob] and starts a new one.
     *
     * Also updates style of all tiers.
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
     * Launches and assigns a job created by [launchUpdateTierStylesJob] to
     * [updateTierListStylesJob].
     */
    fun updateTierStyles() {
        updateTierListStylesJob = launchUpdateTierStylesJob()
    }

    /**
     * Creates and starts [Job] that updates styles of all tiers and notifies UI about the updates.
     *
     * @return created job.
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
     * Converts [DragEffect] to the [TierListEvent] and notifies UI about it.
     *
     * @param effect drag effect to process.
     */
    private fun processDragEffect(effect: DragEffect) {
        _tierListEvent.value = tierListProcessor.processDragEffect(effect)
    }

    /**
     * Attempts to save [tierList] to file. On success - notifies observers that tier list is ready
     * to be shared. On error - notifies observers about it.
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
     * Attempts to save [tierList] to file. On success - notifies observers that tier list is ready
     * to be viewed. On error - notifies observers about it.
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
     * Generates bitmap from [tierList] and saves it to a file. Notifies observers about loading
     * state.
     *
     * @return Content [Uri] of the created file or **null** in case of error.
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
     * Inserts images at the start of the backlog and notifies UI about the updates.
     *
     * @param images images to insert.
     */
    fun insertImagesToBacklog(images: List<Image>) {
        if (images.isEmpty()) return
        Timber.i("Inserting images to backlog: $images")
        tierList.backlogImages.addAll(0, images)
        _tierListEvent.value = BacklogImagesAdded
    }
}