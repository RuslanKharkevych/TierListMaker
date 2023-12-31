package me.khruslan.tierlistmaker.presentation.utils.hints.dashboard

import android.app.Activity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.doOnPreDraw
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.presentation.screens.home.DashboardFragment
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintGroup

/**
 * A group of hints for [DashboardFragment].
 *
 * @property binding binding of the fragment.
 * @param activity an activity that hosts the fragment.
 */
class DashboardHintGroup(
    private val activity: Activity,
    private val binding: FragmentDashboardBinding
) : HintGroup<DashboardHintStep>(
    name = NAME,
    activity = activity,
    steps = DashboardHintStep.entries
) {

    private companion object {
        private const val NAME = "DashboardHintGroup"
    }

    override fun hintFactory(): HintFactory<DashboardHintStep> {
        return DashboardHintFactory(activity, binding)
    }

    override fun show(step: DashboardHintStep) {
        prepareForShowing {
            showAddNewListButton()
            super.show(step)
        }
    }

    /**
     * Prepares the UI for showing hint.
     * 1. Waits until the view tree is about to be drawn.
     * 2. Scrolls to the first tier list.
     *
     * @param onReady invoked when hint is ready to be shown.
     */
    private fun prepareForShowing(onReady: () -> Unit) {
        binding.root.doOnPreDraw {
            binding.listTierLists.scrollToPosition(0)
            onReady()
        }
    }

    /**
     * Shows "Add new list" floating action button.
     */
    private fun showAddNewListButton() {
        val params = binding.btnAddNewList.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as HideBottomViewOnScrollBehavior
        behavior.slideUp(binding.btnAddNewList, false)
    }
}