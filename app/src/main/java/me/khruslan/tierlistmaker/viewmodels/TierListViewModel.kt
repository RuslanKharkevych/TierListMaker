package me.khruslan.tierlistmaker.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.effects.*
import me.khruslan.tierlistmaker.data.state.LoadingProgress
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.repository.file.FileManager
import me.khruslan.tierlistmaker.repository.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.repository.tierlist.tier.TierStyleProvider
import me.khruslan.tierlistmaker.ui.screens.tierlist.TierListFragment
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.khruslan.tierlistmaker.utils.extensions.require
import me.khruslan.tierlistmaker.work.SaveTierListArgsProvider
import me.khruslan.tierlistmaker.work.SaveTierListWorker
import java.io.File
import javax.inject.Inject

private const val KEY_TIER_LIST = "tierList"

/**
 * [ViewModel] for [TierListFragment].
 *
 * @property savedStateHandle handle for saving [TierList].
 * @property dragPocket pocket for the [DragData].
 * @property fileManager manager for saving image files.
 * @property tierListProcessor processor of drag effects.
 * @property tierStyleProvider provider of tier styles.
 * @property saveTierListArgsProvider provider of [SaveTierListWorker] arguments.
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
    private val saveTierListArgsProvider: SaveTierListArgsProvider
) : AndroidViewModel(application) {

    /**
     * Initial [TierList] instance obtained from navigation arguments.
     */
    val tierList: TierList = savedStateHandle.require(KEY_TIER_LIST)

    private val _tierListEvent by lazy { LiveEvent<TierListEvent>() }
    private val _loadingProgressLiveData by lazy { MutableLiveData<LoadingProgress?>() }

    /**
     * [LiveData] that notifies observers about the tier list events.
     */
    val tierListEvent: LiveData<TierListEvent> get() = _tierListEvent

    /**
     * [LiveData] that notifies observers about the progress of loading image files.
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
        tierListProcessor.setTierList(tierList)
        updateTierStyles()
    }

    /**
     * Reduces [TierList.zoomValue] by one and notifies UI.
     */
    fun zoomIn() {
        tierList.zoomValue--
        _tierListEvent.value = ImageSizeUpdated(imageSize)
    }

    /**
     * Increases [TierList.zoomValue] by one and notifies UI.
     */
    fun zoomOut() {
        tierList.zoomValue++
        _tierListEvent.value = ImageSizeUpdated(imageSize)
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
     * Ends drag without dropping. Pops [DragPocket.shadow]
     * and notified the UI that it should be restored.
     */
    fun endDrag() {
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
            val images = imageUris.mapIndexed { index, uri ->
                val file = fileManager.createImageFileFromUri(uri)
                createImage(file).also {
                    _loadingProgressLiveData.postValue(
                        LoadingProgress(currentItem = index + 1, totalItems = imageUris.size)
                    )
                }
            }

            tierList.backlogImages.addAll(0, images)
            _tierListEvent.value = BacklogImagesAdded
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
     * Adds empty [Tier] and notifies the UI that it was inserted.
     *
     * Also updates style of all tiers.
     */
    fun addNewTier() {
        tierList.tiers += Tier()
        _tierListEvent.value = TierInserted(tierList.tiers.lastIndex)
        updateTierStyles()
    }

    /**
     * Updates style of all tiers and notifies UI about the updates.
     */
    private fun updateTierStyles() {
        viewModelScope.launch {
            val styles = tierStyleProvider.getTierStyles(tierList.tiers.size)
            tierList.tiers.forEachIndexed { index, tier ->
                tier.style = styles[index]
            }
            _tierListEvent.postValue(TierListChanged)
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
     * Enqueues one-time work request that saves [TierList] in the local storage.
     */
    fun enqueueSaveTierListWork() {
        val workRequest = OneTimeWorkRequest.from(SaveTierListWorker::class.java)
        saveTierListArgsProvider.tierList = tierList
        WorkManager.getInstance(getApplication()).enqueue(workRequest)
    }
}