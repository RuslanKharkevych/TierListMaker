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