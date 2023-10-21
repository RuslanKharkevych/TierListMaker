package me.khruslan.tierlistmaker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.khruslan.tierlistmaker.ui.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.theme.ThemeManager
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for [HomeActivity].
 *
 * @property themeManager manager for changing application's theme at runtime.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val themeManager: ThemeManager) : ViewModel() {

    init {
        Timber.i("HomeViewModel initialized")
    }

    override fun onCleared() {
        Timber.i("HomeViewModel cleared")
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