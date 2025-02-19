package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.utils.hints.collection.CollectionHintStep
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintStep
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep
import me.khruslan.tierlistmaker.presentation.utils.setOnPreferenceClickListener
import me.khruslan.tierlistmaker.presentation.viewmodels.HomeActivityViewModel

/**
 * Preference fragment that represents "Hints" option in the navigation drawer.
 *
 * Contains "Collection hints" and "Tier list hints" sections.
 *
 * @constructor Default no-arg constructor.
 */
class HintsFragment : PreferenceFragmentCompat() {

    /**
     * View model of the hosting activity.
     *
     * Used for sending events to other fragments.
     */
    private val activityViewModel: HomeActivityViewModel by activityViewModels()

    /**
     * Inflates preferences XML and initializes click listeners.
     *
     * Called during [PreferenceFragmentCompat.onCreate] to supply the preferences for this
     * fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @param rootKey If non-null, this preference fragment should be rooted at the
     * [PreferenceScreen] with this key.
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_hints, rootKey)
        initClickListeners()
    }

    /**
     * Initializes click listeners for all hint preferences.
     *
     * On preference click navigates back and shows hint.
     */
    private fun initClickListeners() {
        initCollectionHintListeners()
        initTierListHintListeners()
    }

    /**
     * Initializes click listeners for collection hints.
     *
     * On preference click navigates back and shows hint.
     */
    private fun initCollectionHintListeners() {
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
     * Initializes click listeners for tier list hints.
     *
     * On preference click navigates back and shows hint.
     */
    private fun initTierListHintListeners() {
        setOnPreferenceClickListener(R.string.pref_reorder_tiers_hint_key) {
            navigateBackAndShowHint(TierListHintStep.ReorderTiers)
        }
        setOnPreferenceClickListener(R.string.pref_remove_tier_list_image_hint_key) {
            navigateBackAndShowHint(TierListHintStep.RemoveImage)
        }
        setOnPreferenceClickListener(R.string.pref_remove_tier_hint_key) {
            navigateBackAndShowHint(TierListHintStep.RemoveTier)
        }
    }

    /**
     * Navigates back and notifies [activityViewModel] observers that hint needs to be shown.
     *
     * @param step Selected hint step.
     */
    private fun navigateBackAndShowHint(step: HintStep) {
        activityViewModel.showHint(step)
        findNavController().popBackStack()
    }
}