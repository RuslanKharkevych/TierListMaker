package me.khruslan.tierlistmaker.viewmodels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import me.khruslan.tierlistmaker.data.drag.*
import me.khruslan.tierlistmaker.data.drag.actions.*
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.utils.RandomImageUrl
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import javax.inject.Inject

@HiltViewModel
class TierListViewModel @Inject constructor(private val dragPocket: DragPocket) : ViewModel() {
    private lateinit var tierList: TierList

    val tierListLiveData = liveData {
        tierList = fetchTierList()
        emit(tierList)
    }

    private val _zoomLiveData by lazy { MutableLiveData<Int>() }
    val zoomLiveData: LiveData<Int> get() = _zoomLiveData

    private val _dragActionLiveData by lazy { MutableLiveData<DragAction>() }
    val dragActionLiveData: LiveData<DragAction> get() = _dragActionLiveData

    private fun fetchTierList() = TierList(
        zoomValue = 5,
        tiers = mutableListOf(
            Tier(
                title = "S",
                color = Color.RED,
                imageUrls = MutableList(5) {
                    RandomImageUrl.generate()
                }
            ),
            Tier(
                title = "A",
                color = Color.YELLOW,
                imageUrls = mutableListOf()
            ),
            Tier(
                title = "B",
                color = Color.GREEN,
                imageUrls = MutableList(3) { RandomImageUrl.generate() }
            )
        ),
        backlogImageUrls = mutableListOf()
    )

    fun zoomIn() {
        tierList.zoomValue--
        _zoomLiveData.value = tierList.zoomValue
    }

    fun zoomOut() {
        tierList.zoomValue++
        _zoomLiveData.value = tierList.zoomValue
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
}