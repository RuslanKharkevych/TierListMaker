package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.utils.hints.collection.CollectionHintStep
import me.khruslan.tierlistmaker.presentation.utils.setOnPreferenceClickListener
import me.khruslan.tierlistmaker.presentation.viewmodels.HomeActivityViewModel

/**
 * Preference fragment that represents "Hints" option in the navigation drawer.
 */
class HintsFragment : PreferenceFragmentCompat() {
    private val activityViewModel: HomeActivityViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_hints, rootKey)
        initClickListeners()
    }

    /**
     * Initializes click listeners for all hint preferences.
     */
    private fun initClickListeners() {
        setOnPreferenceClickListener(R.string.pref_reorder_tier_lists_hint_key) {
            navigateBackAndShowHint(CollectionHintStep.ReorderTierLists)
        }
        setOnPreferenceClickListener(R.string.pref_add_new_tier_list_hint_key) {
            navigateBackAndShowHint(CollectionHintStep.AddNewTierList)
        }
        setOnPreferenceClickListener(R.string.pref_remove_tier_lists_hint_key) {
            navigateBackAndShowHint(CollectionHintStep.RemoveTierList)
        }
    }

    /**
     * Navigates back and notifies [activityViewModel] observers that hint needs to be shown.
     *
     * @param step selected hint step.
     */
    private fun navigateBackAndShowHint(step: CollectionHintStep) {
        activityViewModel.showHint(step)
        findNavController().popBackStack()
    }
}