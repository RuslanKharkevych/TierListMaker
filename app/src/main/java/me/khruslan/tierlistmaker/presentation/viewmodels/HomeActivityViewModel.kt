package me.khruslan.tierlistmaker.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for [HomeActivity].
 *
 * @property themeManager manager for changing application's theme at runtime.
 */
@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val themeManager: ThemeManager
) : ViewModel() {

    init {
        Timber.i("HomeActivityViewModel initialized")
    }

    override fun onCleared() {
        Timber.i("HomeActivityViewModel cleared")
    }

    /**
     * Toggles light/dark theme. Applies changes and saves user preference.
     */
    fun toggleTheme() {
        viewModelScope.launch {
            themeManager.toggleTheme()
        }
    }
}