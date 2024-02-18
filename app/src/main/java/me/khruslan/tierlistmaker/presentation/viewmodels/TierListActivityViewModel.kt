package me.khruslan.tierlistmaker.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelper
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * View model for [TierListActivity].
 *
 * Obtains tier list from the navigation arguments and saves it in the database on need.
 *
 * @param savedStateHandle Provides navigation arguments.
 * @property databaseHelper Saves tier list in the database.
 * @constructor Creates view model with all dependencies.
 */
@HiltViewModel
class TierListActivityViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    /**
     * Navigation argument keys.
     *
     * These keys must be passed as extras when navigating to the [TierListActivity]. Inside the
     * view model, the arguments can be obtained from the saved state handle.
     */
    private companion object NavArgKeys {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"
    }

    /**
     * Initial tier list instance obtained from navigation arguments.
     *
     * @throws [IllegalStateException] If argument was not found.
     */
    private val tierList: TierList = savedStateHandle[EXTRA_TIER_LIST]
        ?: throw IllegalStateException("$savedStateHandle doesn't contain extra: $EXTRA_TIER_LIST")

    init {
        Timber.i("TierListActivityViewModel initialized")
    }

    /**
     * Logs the onCleared lifecycle event.
     *
     * Called when this view model is no longer used and will be destroyed.
     */
    override fun onCleared() {
        Timber.i("TierListActivityViewModel cleared")
    }

    /**
     * Synchronously persists tier list in the local storage.
     */
    fun saveTierList() {
        runBlocking {
            databaseHelper.saveTierList(tierList)
        }
    }
}