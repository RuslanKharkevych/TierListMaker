package me.khruslan.tierlistmaker.presentation.utils.tierlist

import android.os.Handler
import android.os.Looper
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.GroupTierListBottomBarBinding

/**
 * Binder that enables / disables buttons in the bottom bar depending on the tier list state.
 *
 * @property binding Bottom bar binding.
 * @property tierList Tier list to bind.
 * @constructor Creates a new binder for provided tier list.
 */
class TierListBottomBarBinder(
    private val binding: GroupTierListBottomBarBinding,
    private val tierList: TierList
) {

    /**
     * Constants for private use.
     */
    private companion object Constants {

        /**
         * The delay in milliseconds, after which "intent" buttons are re-enabled.
         *
         * This is necessary to prevent clicks while intent is launching.
         */
        private const val INTENT_BUTTONS_ENABLE_DELAY = 2000L
    }

    /**
     * Resources associated with the root view.
     */
    private val resources
        get() = binding.root.resources

    init {
        invalidateAddNewTierButton()
        invalidateZoomButtons()
        setIntentButtonsEnabledInternal(true)
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
     *
     * - Disables "Zoom in" button if minimal zoom value has been reached.
     * - Disables "Zoom out" button if maximal zoom value has been reached.
     */
    fun invalidateZoomButtons() {
        binding.btnZoomIn.isEnabled =
            tierList.zoomValue > resources.getInteger(R.integer.min_zoom_value)
        binding.btnZoomOut.isEnabled =
            tierList.zoomValue < resources.getInteger(R.integer.max_zoom_value)
    }

    /**
     * Requests enabling or disabling of "intent" buttons.
     *
     * "Intent" buttons are those that are supposed to launch intent on click. Note that buttons are
     * disabled immediately, but enabled only after [INTENT_BUTTONS_ENABLE_DELAY].
     *
     * @param enabled Whether buttons should be enabled or disabled.
     */
    fun setIntentButtonsEnabled(enabled: Boolean) {
        if (enabled) {
            Handler(Looper.getMainLooper()).postDelayed({
                setIntentButtonsEnabledInternal(true)
            }, INTENT_BUTTONS_ENABLE_DELAY)
        } else {
            setIntentButtonsEnabledInternal(false)
        }
    }

    /**
     * Immediately enables / disables "intent" buttons.
     *
     * "Intent" buttons include "Add new image", "Share" and "View" buttons.
     *
     * @param enabled Whether buttons should be enabled or disabled.
     */
    private fun setIntentButtonsEnabledInternal(enabled: Boolean) {
        binding.btnAddNewImage.isEnabled = enabled
        binding.btnShare.isEnabled = enabled
        binding.btnView.isEnabled = enabled
    }
}