package me.khruslan.tierlistmaker.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator
import me.khruslan.tierlistmaker.presentation.models.ListState
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultException
import me.khruslan.tierlistmaker.presentation.screens.home.CollectionFragment
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.util.swap
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for [CollectionFragment].
 *
 * @property databaseHelper database helper.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @property tierListCreator creator of new tier lists.
 */
@HiltViewModel
class CollectionViewModel @Inject constructor(
    application: Application,
    private val databaseHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider,
    private val tierListCreator: TierListCreator
) : AndroidViewModel(application) {

    /**
     * List of [TierList] objects that are used for passing to the next screen.
     */
    private lateinit var tierLists: MutableList<TierList>

    /**
     * List of [TierList.Preview] object that are displayed on UI.
     */
    private lateinit var tierListPreviews: MutableList<TierList.Preview>

    private val _addPreviewEvent by lazy { LiveEvent<Int>() }
    private val _updatePreviewEvent by lazy { LiveEvent<Int>() }
    private val _listStateLiveData by lazy { MutableLiveData<ListState>() }
    private val _errorEvent by lazy { LiveEvent<Int>() }
    private val _tierListPreviewsLiveData = MutableLiveData<MutableList<TierList.Preview>>()
    private val _tierListCreatedEvent by lazy { LiveEvent<TierList>() }

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
     * [LiveData] that notifies observers that the new tier list has been created.
     */
    val tierListCreatedEvent: LiveData<TierList> get() = _tierListCreatedEvent

    init {
        Timber.i("CollectionViewModel initialized")
        loadTierListPreviews()
    }

    override fun onCleared() {
        Timber.i("CollectionViewModel cleared")
    }

    /**
     * Creates an empty [TierList] asynchronously and triggers [tierListCreatedEvent].
     *
     * @param title name of the tier list.
     */
    fun createNewTierList(title: String) {
        Timber.i("Creating new tier list with title: $title")
        viewModelScope.launch {
            val tierList = tierListCreator.newTierList(title)
            _tierListCreatedEvent.value = tierList
            Timber.i("Created new tier list: $tierList")
        }
    }

    /**
     * Handles [TierList] that was either added or updated. Persists it in the local storage and
     * notifies UI about the updates
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
                Timber.i("Added new tier list: $tierList")
            } else {
                tierLists[index] = tierList
                tierListPreviews[index] = tierList.preview
                _updatePreviewEvent.postValue(index)
                Timber.i("Updated tier list at index $index: $tierList")
            }
            Timber.i("Updated tier lists: $tierLists")
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
            val result = databaseHelper.saveTierList(tierList)
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
     * Loads tier lists from [databaseHelper] and returns the result. Updates [listStateLiveData]
     * after loading is complete.
     *
     * @return loaded tier lists or empty list in case of error.
     */
    private suspend fun loadTierLists(): MutableList<TierList> {
        return databaseHelper.getTierLists()?.also { list ->
            _listStateLiveData.value = if (list.isEmpty()) ListState.Empty else ListState.Filled
        } ?: run {
            _listStateLiveData.value = ListState.Failed
            mutableListOf()
        }
    }

    /**
     * Loads [tierLists] from [databaseHelper], maps [tierListPreviews] and updates
     * [tierListPreviewsLiveData].
     */
    private fun loadTierListPreviews() {
        Timber.i("Loading tier list previews")
        viewModelScope.launch {
            delay(HomeActivity.SPLASH_SCREEN_EXIT_ANIM_DURATION)
            tierLists = loadTierLists()
            tierListPreviews = tierLists.map { it.preview }.toMutableList()
            _tierListPreviewsLiveData.value = tierListPreviews
            Timber.i("Loaded tier list previews: $tierListPreviews")
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
        Timber.i("Swapped tier lists at indices $firstIndex and $secondIndex")
        Timber.i("Updated tier lists: $tierLists")
        updateTierLists()
    }

    /**
     * Updates tier lists in the local storage. Triggers [errorEvent] on failure.
     */
    private fun updateTierLists() {
        viewModelScope.launch {
            val result = databaseHelper.updateTierLists(tierLists)
            if (!result) _errorEvent.value = R.string.update_tier_lists_error_message
        }
    }

    /**
     * Removes tier list from the local storage. Triggers [errorEvent] in case of failure.
     *
     * @param index position of the tier list to remove.
     */
    fun removeTierList(index: Int) {
        Timber.i("Removing tier list at position $index")
        viewModelScope.launch {
            val tierList = tierLists[index]
            tierLists.remove(tierList)
            Timber.i("Updated tier lists: $tierList")
            if (tierLists.isEmpty()) _listStateLiveData.value = ListState.Empty

            val result = databaseHelper.removeTierListById(tierList.id)
            if (!result) _errorEvent.value = R.string.remove_tier_list_error_message
        }
    }
}