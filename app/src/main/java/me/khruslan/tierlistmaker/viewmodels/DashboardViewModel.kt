package me.khruslan.tierlistmaker.viewmodels

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.db.PaperRepository
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.ui.screens.home.DashboardFragment
import java.util.*
import javax.inject.Inject

/**
 * [ViewModel] for [DashboardFragment].
 *
 * @property paperRepository local storage repository.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @param application [Application] instance.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val paperRepository: PaperRepository,
    private val dispatcherProvider: DispatcherProvider
) : AndroidViewModel(application) {

    /**
     * List of [TierList] objects that are used for passing to the next screen.
     */
    private lateinit var tierLists: MutableList<TierList>

    /**
     * List of [TierList.Preview] object that are displayed on UI.
     */
    private lateinit var tierListPreviews: MutableList<TierList.Preview>

    /**
     * [LiveData] that loads [tierLists] from [paperRepository]
     * and emits [tierListPreviews] to the observers.
     */
    val tierListPreviewsLiveData = liveData {
        // TODO: Set loading state
        tierLists = paperRepository.getTierLists()?.also { list ->
            if (list.isEmpty()) {
                // TODO: Set empty state if needed
            }
        } ?: run {
            // TODO: Set error state
            mutableListOf()
        }

        tierListPreviews = tierLists.map { it.preview }.toMutableList()
        emit(tierListPreviews)
    }

    private val _addPreviewLiveData by lazy { MutableLiveData<Int>() }
    private val _updatePreviewsLiveData by lazy { MutableLiveData<Unit>() }

    /**
     * [LiveData] that notifies observers about the position of the newly added preview.
     */
    val addPreviewLiveData: LiveData<Int> get() = _addPreviewLiveData

    /**
     * [LiveData] that notifies observers that previews were updated.
     */
    val updatePreviewsLiveData: LiveData<Unit> get() = _updatePreviewsLiveData

    /**
     * Creates an empty [TierList].
     *
     * @param title name of the tier list.
     * @return Created tier list.
     */
    fun createNewTierList(title: String): TierList {
        return TierList(
            id = UUID.randomUUID().toString(),
            title = title,
            zoomValue = 5,
            tiers = MutableList(5) { Tier() },
            backlogImages = mutableListOf()
        )
    }

    /**
     * Handles [TierList] that was either added or updated.
     *
     * Persists it in the local storage and notifies UI about the updates
     *
     * @param tierList new or updated tier list.
     */
    fun handleTierListResult(tierList: TierList) {
        addOrUpdateTierList(tierList)
        saveTierList(tierList)
    }

    /**
     * Updates [tierLists] and [tierListPreviews] with added or updated [TierList].
     * Notifies UI about the updates.
     *
     * @param tierList new or updated tier list.
     */
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

    /**
     * Saves added or updated [TierList] in the local storage.
     *
     * @param tierList new or updated tier list.
     */
    private fun saveTierList(tierList: TierList) {
        viewModelScope.launch {
            val result = paperRepository.saveTierList(tierList)
            if (!result) {
                // TODO: Show error snackBar
            }
        }
    }

    /**
     * Obtains [TierList] by [position].
     *
     * @param position position of the tier lists.
     * @return obtained tier list.
     */
    fun getTierListByPosition(position: Int): TierList {
        return tierLists[position]
    }
}