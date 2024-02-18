package me.khruslan.tierlistmaker.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator
import me.khruslan.tierlistmaker.presentation.models.ListState
import me.khruslan.tierlistmaker.presentation.screens.home.CollectionFragment
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultException
import me.khruslan.tierlistmaker.util.swap
import timber.log.Timber
import javax.inject.Inject

/**
 * View model for [CollectionFragment].
 *
 * Manages tier list previews.
 *
 * @property databaseHelper Manages tier lists in the database.
 * @property dispatcherProvider Provides default dispatcher.
 * @property tierListCreator Creates new tier lists.
 * @constructor Creates view model with all dependencies.
 */
@HiltViewModel
class CollectionViewModel @Inject constructor(
    application: Application,
    private val databaseHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider,
    private val tierListCreator: TierListCreator
) : AndroidViewModel(application) {

    /**
     * Tier lists that are used for passing to the next screen.
     */
    private lateinit var tierLists: MutableList<TierList>

    /**
     * Tier list previews that are displayed on UI.
     */
    private lateinit var tierListPreviews: MutableList<TierList.Preview>

    /**
     * Mutable reference to [addPreviewEvent].
     */
    private val _addPreviewEvent by lazy { LiveEvent<Int>() }

    /**
     * Mutable reference to [updatePreviewEvent].
     */
    private val _updatePreviewEvent by lazy { LiveEvent<Int>() }

    /**
     * Mutable reference to [listStateLiveData].
     */
    private val _listStateLiveData by lazy { MutableLiveData<ListState>() }

    /**
     * Mutable reference to [errorEvent].
     */
    private val _errorEvent by lazy { LiveEvent<Int>() }

    /**
     * Mutable reference to [tierListPreviewsLiveData].
     */
    private val _tierListPreviewsLiveData = MutableLiveData<MutableList<TierList.Preview>>()

    /**
     * Mutable reference to [tierListCreatedEvent].
     */
    private val _tierListCreatedEvent by lazy { LiveEvent<TierList>() }

    /**
     * Live data that notifies observers about the position of the newly added preview.
     */
    val addPreviewEvent: LiveData<Int> get() = _addPreviewEvent

    /**
     * Live data that notifies observers about the position of the updated preview.
     */
    val updatePreviewEvent: LiveData<Int> get() = _updatePreviewEvent

    /**
     * Live data that notifies observer about the state of the list of previews.
     */
    val listStateLiveData: LiveData<ListState> get() = _listStateLiveData

    /**
     * Live data that notifies observer about errors.
     *
     * The value of the live data is a string resource of the error message.
     */
    val errorEvent: LiveData<Int> get() = _errorEvent

    /**
     * Live data that notifies observers about the tier list previews.
     */
    val tierListPreviewsLiveData: LiveData<MutableList<TierList.Preview>>
        get() = _tierListPreviewsLiveData

    /**
     * Live data that notifies observers that the new tier list has been created.
     */
    val tierListCreatedEvent: LiveData<TierList> get() = _tierListCreatedEvent

    init {
        Timber.i("CollectionViewModel initialized")
        loadTierListPreviews()
    }

    /**
     * Logs the onCleared lifecycle event.
     *
     * Called when this view model is no longer used and will be destroyed.
     */
    override fun onCleared() {
        Timber.i("CollectionViewModel cleared")
    }

    /**
     * Asynchronously creates an empty tier list.
     *
     * Triggers [tierListCreatedEvent].
     *
     * @param title Name of the tier list.
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
     * Handles tier list that was either added or updated.
     *
     * Persists it in the local storage and notifies UI about the updates.
     *
     * @param tierList New or updated tier list.
     * @throws [TierListResultException] If tier list is null.
     */
    fun handleTierListResult(tierList: TierList?) {
        if (tierList == null) {
            throw TierListResultException("tierListLauncher: result is null")
        }

        addOrUpdateTierList(tierList)
        saveTierList(tierList)
    }

    /**
     * Asynchronously updates tier lists with added or updated tier list.
     *
     * If tier list is found by ID:
     * - Updates the tier list in [tierLists].
     * - Updates the respective preview in [tierListPreviews].
     * - Triggers [updatePreviewEvent] update.
     *
     * If tier list is not found by ID:
     * - Inserts the tier list into [tierLists].
     * - Inserts the respective preview into [tierListPreviews].
     * - Updates [listStateLiveData] with [ListState.Filled].
     * - Triggers [updatePreviewEvent] update.
     *
     * @param tierList New or updated tier list.
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
     * Asynchronously saves added or updated tier list in the local storage.
     *
     * Triggers [errorEvent] in case of error. Note that in such case [tierLists] will become out of
     * sync with the real data.
     *
     * @param tierList New or updated tier list.
     */
    private fun saveTierList(tierList: TierList) {
        viewModelScope.launch {
            val result = databaseHelper.saveTierList(tierList)
            if (!result) _errorEvent.value = R.string.save_tier_list_error_message
        }
    }

    /**
     * Obtains tier list by position.
     *
     * @param position Position of the tier list.
     * @return Obtained tier list.
     */
    fun getTierListByPosition(position: Int): TierList {
        return tierLists[position]
    }

    /**
     * Asynchronously loads tier lists from database and returns the result.
     *
     * Updates [listStateLiveData] after loading is complete.
     *
     * @return Loaded tier lists or empty list in case of error.
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
     *
     * The function is delayed to make sure that splash screen exit animation finishes gracefully.
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
     * Re-loads tier list previews.
     *
     * Updates [listStateLiveData] with [ListState.Loading] and calls [loadTierListPreviews].
     */
    fun refreshPreviews() {
        _listStateLiveData.value = ListState.Loading
        loadTierListPreviews()
    }

    /**
     * Swaps order of two tier lists.
     *
     * The updated order is then saved in the database.
     *
     * @param firstIndex Position of the first tier list.
     * @param secondIndex Position of the second tier list.
     */
    fun swapTierLists(firstIndex: Int, secondIndex: Int) {
        tierLists.swap(firstIndex, secondIndex)
        Timber.i("Swapped tier lists at indices $firstIndex and $secondIndex")
        Timber.i("Updated tier lists: $tierLists")
        updateTierLists()
    }

    /**
     * Asynchronously updates tier lists in the local storage.
     *
     * Triggers [errorEvent] on database transaction failure. Note that in such case [tierLists]
     * will become out of sync with the real data.
     */
    private fun updateTierLists() {
        viewModelScope.launch {
            val result = databaseHelper.updateTierLists(tierLists)
            if (!result) _errorEvent.value = R.string.update_tier_lists_error_message
        }
    }

    /**
     * Asynchronously removes tier list from the local storage.
     *
     * If the last tier list is being removed, updates [listStateLiveData] with [ListState.Empty].
     * If database transaction fails, triggers [errorEvent]. Note that the tier list will always be
     * removed from [tierLists], regardless of the transaction result.
     *
     * @param index Position of the tier list to remove.
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