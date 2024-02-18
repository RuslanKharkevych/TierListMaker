package me.khruslan.tierlistmaker.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.presentation.utils.hints.collection.CollectionHintStep
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import timber.log.Timber
import javax.inject.Inject

/**
 * View model for [HomeActivity].
 *
 * Responsible for changing theme and showing hints.
 *
 * @property themeManager Toggles application theme.
 * @constructor Creates view model with all dependencies.
 */
@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val themeManager: ThemeManager
) : ViewModel() {

    /**
     * Mutable reference to [hintEvent].
     */
    private val _hintEvent by lazy {
        LiveEvent<CollectionHintStep>(LiveEventConfig.PreferFirstObserver)
    }

    /**
     * Live data that is updated when the collection hint needs to be shown.
     *
     * The value corresponds to the step of the hint within its group.
     */
    val hintEvent: LiveData<CollectionHintStep> get() = _hintEvent

    init {
        Timber.i("HomeActivityViewModel initialized")
    }

    /**
     * Logs the onCleared lifecycle event.
     *
     * Called when this view model is no longer used and will be destroyed.
     */
    override fun onCleared() {
        Timber.i("HomeActivityViewModel cleared")
    }

    /**
     * Toggles light/dark theme.
     *
     * Asynchronously changes and saves user preference.
     */
    fun toggleTheme() {
        viewModelScope.launch {
            themeManager.toggleTheme()
        }
    }

    /**
     * Notifies the observers that hint needs to be shown.
     *
     * @param step The step of the hint within its group.
     */
    fun showHint(step: CollectionHintStep) {
        _hintEvent.value = step
    }
}