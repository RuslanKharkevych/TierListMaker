package me.khruslan.tierlistmaker.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.LoadingProgress
import me.khruslan.tierlistmaker.data.drag.*
import me.khruslan.tierlistmaker.data.drag.actions.*
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.repository.file.FileManager
import me.khruslan.tierlistmaker.repository.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.repository.tierlist.tier.TierStyleProvider
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TierListViewModel @Inject constructor(
    application: Application,
    private val dragPocket: DragPocket,
    private val fileManager: FileManager,
    private val tierListProcessor: TierListProcessor,
    private val tierStyleProvider: TierStyleProvider
) : AndroidViewModel(application) {
    private lateinit var tierList: TierList
    val imageSize get() = displayWidth / tierList.zoomValue

    val tierListLiveData = liveData {
        tierList = fetchTierList()
        tierListProcessor.setTierList(tierList)
        emit(tierList)
    }

    private val _tierListEventLiveData by lazy { MutableLiveData<TierListEvent>() }
    val tierListEventLiveData: LiveData<TierListEvent> get() = _tierListEventLiveData

    private val _loadingProgressLiveData by lazy { MutableLiveData<LoadingProgress?>() }
    val loadingProgressLiveData: LiveData<LoadingProgress?> get() = _loadingProgressLiveData

    private val displayWidth by lazy {
        getApplication<Application>().displayWidthPixels
    }

    private fun fetchTierList() = TierList(
        zoomValue = 5,
        tiers = mutableListOf(),
        backlogImages = mutableListOf()
    )

    fun zoomIn() {
        tierList.zoomValue--
        _tierListEventLiveData.value = ImageSizeUpdated(imageSize)
    }

    fun zoomOut() {
        tierList.zoomValue++
        _tierListEventLiveData.value = ImageSizeUpdated(imageSize)
    }

    fun startDrag(dragData: ImageDragData) {
        dragPocket.shadow = dragData
        processDragAction(RemoveAction.create(dragData))
    }

    fun updateDragTarget(newTarget: DragData?) {
        val oldTarget = dragPocket.target
        if (oldTarget != null) processDragAction(RemoveAction.create(oldTarget))

        dragPocket.target = newTarget
        if (newTarget != null) processDragAction(HighlightAction.create(newTarget))
    }

    fun dropImage(dragData: ImageDragData) {
        val target = dragPocket.target

        if (target != null) {
            processDragAction(UpdateAction.create(dragData, target))
        } else {
            val cachedTarget = dragPocket.cachedTarget ?: return
            processDragAction(InsertAction.create(dragData, cachedTarget))
        }
    }

    fun endDrag() {
        val shadow = dragPocket.shadow ?: return
        processDragAction(InsertAction.create(shadow))
    }

    fun saveImages(imageUris: List<Uri>) {
        viewModelScope.launch {
            val images = imageUris.mapIndexed { index, uri ->
                val file = fileManager.createFileFromUri(uri)
                createImage(file).also {
                    _loadingProgressLiveData.postValue(
                        LoadingProgress(currentItem = index + 1, totalItems = imageUris.size)
                    )
                }
            }

            tierList.backlogImages.addAll(0, images)
            _tierListEventLiveData.value = BacklogImagesAdded
            _loadingProgressLiveData.postValue(null)
        }
    }

    private fun createImage(file: File?) = if (file != null) {
        StorageImage(file)
    } else {
        ResourceImage(resId = R.drawable.ic_broken_image)
    }

    fun addNewTier() {
        tierList.tiers += Tier()
        _tierListEventLiveData.value = TierInserted(tierList.tiers.size)
        viewModelScope.launch { updateTierStyles() }
    }

    private suspend fun updateTierStyles() {
        val styles = tierStyleProvider.getTierStyles(tierList.tiers.size)
        tierList.tiers.forEachIndexed { index, tier ->
            tier.style = styles[index]
        }
        _tierListEventLiveData.postValue(TierListChanged)
    }

    private fun processDragAction(action: DragAction) {
        _tierListEventLiveData.value = tierListProcessor.processDragAction(action)
    }
}