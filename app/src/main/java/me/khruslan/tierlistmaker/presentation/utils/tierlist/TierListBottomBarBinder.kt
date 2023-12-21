package me.khruslan.tierlistmaker.presentation.utils.tierlist

import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.GroupTierListBottomBarBinding

/**
 * Binder that enables / disables buttons in the bottom bar depending on the tier list state.
 *
 * @property binding bottom bar binding.
 * @property tierList tier list to bind.
 */
class TierListBottomBarBinder(
    private val binding: GroupTierListBottomBarBinding,
    private val tierList: TierList
) {

    /**
     * Resources associated with the root view.
     */
    private val resources
        get() = binding.root.resources

    init {
        invalidateAddNewTierButton()
        invalidateZoomButtons()
    }

    /**
     * Disables "Add new tier" button if maximum number of tiers has been reached.
     */
    fun invalidateAddNewTierButton() {
        binding.btnAddNewTier.isEnabled =
            tierList.tiers.size < resources.getInteger(R.integer.max_tiers_count)
    }

    /**
     * Invalidates zoom buttons.
     * - Disables "Zoom in" button if minimal zoom value has been reached.
     * - Disables "Zoom out" button if maximal zoom value has been reached.
     */
    fun invalidateZoomButtons() {
        binding.btnZoomIn.isEnabled =
            tierList.zoomValue > resources.getInteger(R.integer.min_zoom_value)
        binding.btnZoomOut.isEnabled =
            tierList.zoomValue < resources.getInteger(R.integer.max_zoom_value)
    }
}