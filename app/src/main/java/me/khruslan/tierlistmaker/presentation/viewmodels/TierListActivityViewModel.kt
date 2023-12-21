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
 * [ViewModel] for [TierListActivity].
 *
 * @param savedStateHandle handle for saving [TierList].
 */
@HiltViewModel
class TierListActivityViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    /**
     * Companion object of [TierListActivityViewModel] used for storing [SavedStateHandle] keys.
     */
    private companion object {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"
    }

    /**
     * Initial [TierList] instance obtained from intent extras.
     */
    private val tierList: TierList = savedStateHandle[EXTRA_TIER_LIST]
        ?: throw IllegalStateException("$savedStateHandle doesn't contain extra: $EXTRA_TIER_LIST")

    init {
        Timber.i("TierListActivityViewModel initialized")
    }

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