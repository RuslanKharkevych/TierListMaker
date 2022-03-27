package me.khruslan.tierlistmaker.viewmodels

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.TierListPreview
import me.khruslan.tierlistmaker.repository.db.PaperRepository
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val paperRepository: PaperRepository,
    private val dispatcherProvider: DispatcherProvidable
) : AndroidViewModel(application) {
    private lateinit var tierLists: MutableList<TierList>
    private lateinit var tierListPreviews: MutableList<TierListPreview>

    val tierListPreviewsLiveData = liveData {
        tierLists = paperRepository.getTierLists()
        tierListPreviews = tierLists.map { it.preview }.toMutableList()
        emit(tierListPreviews)
    }

    private val _addPreviewLiveData by lazy { MutableLiveData<Int>() }
    val addPreviewLiveData: LiveData<Int> get() = _addPreviewLiveData

    private val _updatePreviewsLiveData by lazy { MutableLiveData<Unit>() }
    val updatePreviewsLiveData: LiveData<Unit> get() = _updatePreviewsLiveData

    fun createNewTierList(title: String) = TierList(
        id = UUID.randomUUID().toString(),
        title = title,
        zoomValue = 5,
        tiers = MutableList(5) { Tier() },
        backlogImages = mutableListOf()
    )

    fun handleTierListResult(tierList: TierList) {
        addOrUpdateTierList(tierList)
        saveTierList(tierList)
    }

    private fun addOrUpdateTierList(tierList: TierList) {
        viewModelScope.launch(dispatcherProvider.default) {
            val index = tierLists.indexOfFirst { it.id == tierList.id }
            if (index == -1) {
                tierLists += tierList
                tierListPreviews += tierList.preview
                _addPreviewLiveData.postValue(tierListPreviews.lastIndex)
            } else {
                tierLists[index] = tierList
                tierListPreviews[index] = tierList.preview
                _updatePreviewsLiveData.postValue(Unit)
            }
        }
    }

    private fun saveTierList(tierList: TierList) = viewModelScope.launch {
        paperRepository.saveTierList(tierList)
    }

    fun getTierListByPosition(position: Int) = tierLists[position]
}