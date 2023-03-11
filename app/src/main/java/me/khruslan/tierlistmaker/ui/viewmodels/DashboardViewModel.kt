package me.khruslan.tierlistmaker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProvider
import me.khruslan.tierlistmaker.data.work.UpdateTierListsWorker
import me.khruslan.tierlistmaker.ui.models.ListState
import me.khruslan.tierlistmaker.ui.navigation.TierListResultException
import me.khruslan.tierlistmaker.ui.screens.home.DashboardFragment
import me.khruslan.tierlistmaker.utils.swap
import java.util.*
import javax.inject.Inject

/**
 * [ViewModel] for [DashboardFragment].
 *
 * @property paperRepository local storage repository.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @property updateTierListsArgsProvider provider of [UpdateTierListsWorker] arguments.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val paperRepository: PaperRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val updateTierListsArgsProvider: UpdateTierListsArgsProvider
) : AndroidViewModel(application) {

    /**
     * List of [TierList] objects that are used for passing to the next screen.
     */
    private lateinit var tierLists: MutableList<TierList>

    /**
     * List of [TierList.Preview] object that are displayed on UI.
     */
    private lateinit var tierListPreviews: MutableList<TierList.Preview>

    init {
        loadTierListPreviews()
    }

    private val _addPreviewEvent by lazy { LiveEvent<Int>() }
    private val _updatePreviewEvent by lazy { LiveEvent<Int>() }
    private val _listStateLiveData by lazy { MutableLiveData<ListState>() }
    private val _errorEvent by lazy { LiveEvent<Int>() }
    private val _tierListPreviewsLiveData = MutableLiveData<MutableList<TierList.Preview>>()

    /**
     * [LiveData] that notifies observers about the position of the newly added preview.
     */
    val addPreviewEvent: LiveData<Int> get() = _addPreviewEvent

    /**
     * [LiveData] that notifies observers about the position of the updated preview.
     */
    val updatePreviewEvent: LiveData<Int> get() = _updatePreviewEvent

    /**
     * [LiveData] that notifies observer about the state of the list of previews.
     */
    val listStateLiveData: LiveData<ListState> get() = _listStateLiveData

    /**
     * [LiveData] that notifies observer about errors. The value of the live data is a string
     * resource of the error message.
     */
    val errorEvent: LiveData<Int> get() = _errorEvent

    /**
     * [LiveData] that notifies observers about the tier list previews.
     */
    val tierListPreviewsLiveData: LiveData<MutableList<TierList.Preview>>
        get() = _tierListPreviewsLiveData

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
    fun handleTierListResult(tierList: TierList?) {
        if (tierList == null) {
            throw TierListResultException("tierListLauncher: result is null")
        }

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
                _listStateLiveData.postValue(ListState.Filled)
                _addPreviewEvent.postValue(tierListPreviews.lastIndex)
            } else {
                tierLists[index] = tierList
                tierListPreviews[index] = tierList.preview
                _updatePreviewEvent.postValue(index)
            }
        }
    }

    /**
     * Saves added or updated [TierList] in the local storage. Triggers [errorEvent] in case of
     * error.
     *
     * @param tierList new or updated tier list.
     */
    private fun saveTierList(tierList: TierList) {
        viewModelScope.launch {
            val result = paperRepository.saveTierList(tierList)
            if (!result) _errorEvent.value = R.string.save_tier_list_error_message
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

    /**
     * Loads tier lists from [paperRepository] and returns the result.
     *
     * Updates [listStateLiveData] after loading is complete.
     *
     * @return loaded tier lists or empty list in case of error.
     */
    private suspend fun loadTierLists(): MutableList<TierList> {
        return paperRepository.getTierLists()?.also { list ->
            _listStateLiveData.value = if (list.isEmpty()) ListState.Empty else ListState.Filled
        } ?: run {
            _listStateLiveData.value = ListState.Failed
            mutableListOf()
        }
    }

    /**
     * Loads [tierLists] from [paperRepository], maps [tierListPreviews] and updates
     * [tierListPreviewsLiveData].
     */
    private fun loadTierListPreviews() {
        viewModelScope.launch {
            tierLists = loadTierLists()
            tierListPreviews = tierLists.map { it.preview }.toMutableList()
            _tierListPreviewsLiveData.value = tierListPreviews
        }
    }

    /**
     * Re-loads tier list previews (see [loadTierListPreviews]) and updates [listStateLiveData]
     * accordingly.
     */
    fun refreshPreviews() {
        _listStateLiveData.value = ListState.Loading
        loadTierListPreviews()
    }

    /**
     * Swaps order of two tier lists.
     *
     * @param firstIndex position of the first tier list.
     * @param secondIndex position of the second tier list.
     */
    fun swapTierLists(firstIndex: Int, secondIndex: Int) {
        tierLists.swap(firstIndex, secondIndex)
    }

    /**
     * Removes tier list from the local storage. Triggers [errorEvent] in case of failure.
     *
     * @param index position of the tier list to remove.
     */
    fun removeTierList(index: Int) {
        viewModelScope.launch {
            val tierList = tierLists[index]
            tierLists.remove(tierList)
            if (tierLists.isEmpty()) _listStateLiveData.value = ListState.Empty

            val result = paperRepository.removeTierListById(tierList.id)
            if (!result) _errorEvent.value = R.string.remove_tier_list_error_message
        }
    }

    /**
     * Enqueues one-time work request that updates tier lists in the local storage.
     */
    fun enqueueUpdateTierListsWork() {
        val workRequest = OneTimeWorkRequest.from(UpdateTierListsWorker::class.java)
        updateTierListsArgsProvider.tierLists = tierLists
        WorkManager.getInstance(getApplication()).enqueue(workRequest)
    }
}