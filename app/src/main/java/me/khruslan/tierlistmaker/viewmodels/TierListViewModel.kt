package me.khruslan.tierlistmaker.viewmodels

import android.app.Application
import android.graphics.Color
import android.net.Uri
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.LoadingProgress
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.actions.*
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.repository.file.FileManager
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TierListViewModel @Inject constructor(
    application: Application,
    private val dragPocket: DragPocket,
    private val fileManager: FileManager
) : AndroidViewModel(application) {
    private lateinit var tierList: TierList
    val imageSize get() = displayWidth / tierList.zoomValue

    val tierListLiveData = liveData {
        tierList = fetchTierList()
        emit(tierList)
    }

    private val _imageSizeLiveData by lazy { MutableLiveData<Int>() }
    val imageSizeLiveData: LiveData<Int> get() = _imageSizeLiveData

    private val _dragActionLiveData by lazy { MutableLiveData<DragAction>() }
    val dragActionLiveData: LiveData<DragAction> get() = _dragActionLiveData

    private val _newImagesLiveData by lazy { MutableLiveData<List<Image>>() }
    val newImagesLiveData: LiveData<List<Image>> get() = _newImagesLiveData

    private val _loadingProgressLiveData by lazy { MutableLiveData<LoadingProgress?>() }
    val loadingProgressLiveData: LiveData<LoadingProgress?> get() = _loadingProgressLiveData

    val targetImage by lazy {
        ResourceImage(resId = R.drawable.ic_crop_free)
    }

    private val displayWidth by lazy {
        getApplication<Application>().displayWidthPixels
    }

    private fun fetchTierList() = TierList(
        zoomValue = 5,
        tiers = mutableListOf(
            Tier(
                title = "S",
                color = Color.RED,
                images = mutableListOf()
            ),
            Tier(
                title = "A",
                color = Color.YELLOW,
                images = mutableListOf()
            ),
            Tier(
                title = "B",
                color = Color.GREEN,
                images = mutableListOf()
            )
        ),
        backlogImages = mutableListOf()
    )

    fun zoomIn() {
        tierList.zoomValue--
        _imageSizeLiveData.value = imageSize
    }

    fun zoomOut() {
        tierList.zoomValue++
        _imageSizeLiveData.value = imageSize
    }

    fun startDrag(dragData: ImageDragData) {
        dragPocket.shadow = dragData
        _dragActionLiveData.value = RemoveAction.create(dragData)
    }

    fun updateDragTarget(newTarget: DragData?) {
        val oldTarget = dragPocket.target
        if (oldTarget != null) _dragActionLiveData.value = RemoveAction.create(oldTarget)

        dragPocket.target = newTarget
        if (newTarget != null) _dragActionLiveData.value = HighlightAction.create(newTarget)
    }

    fun dropImage(dragData: ImageDragData) {
        val target = dragPocket.target

        if (target != null) {
            _dragActionLiveData.value = UpdateAction.create(dragData, target)
        } else {
            val cachedTarget = dragPocket.cachedTarget ?: return
            _dragActionLiveData.value = InsertAction.create(dragData, cachedTarget)
        }
    }

    fun endDrag() {
        val shadow = dragPocket.shadow ?: return
        _dragActionLiveData.value = InsertAction.create(shadow)
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

            _newImagesLiveData.postValue(images)
            _loadingProgressLiveData.postValue(null)
        }
    }

    private fun createImage(file: File?) = if (file != null) {
        StorageImage(file)
    } else {
        ResourceImage(resId = R.drawable.ic_broken_image)
    }
}