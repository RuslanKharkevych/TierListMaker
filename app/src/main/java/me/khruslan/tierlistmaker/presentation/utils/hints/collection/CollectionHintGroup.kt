package me.khruslan.tierlistmaker.presentation.utils.hints.collection

import android.app.Activity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.doOnPreDraw
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import me.khruslan.tierlistmaker.databinding.FragmentCollectionBinding
import me.khruslan.tierlistmaker.presentation.screens.home.CollectionFragment
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintGroup

/**
 * A group of hints for [CollectionFragment].
 *
 * @property binding Binding of the fragment.
 * @param activity An activity that hosts the fragment.
 * @constructor Creates a new hint group.
 */
class CollectionHintGroup(
    private val activity: Activity,
    private val binding: FragmentCollectionBinding
) : HintGroup<CollectionHintStep>(
    name = NAME,
    activity = activity,
    steps = CollectionHintStep.entries
) {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * The debug name of this hint group.
         */
        private const val NAME = "CollectionHintGroup"
    }

    /**
     * Provides collection hint factory factory.
     *
     * @return Provided factory.
     */
    override fun hintFactory(): HintFactory<CollectionHintStep> {
        return CollectionHintFactory(activity, binding)
    }

    /**
     * Shows the hint group starting with the given step.
     *
     * Note that step is shown only after UI is ready.
     *
     * @param step Step to be shown.
     */
    override fun show(step: CollectionHintStep) {
        prepareForShowing {
            showAddNewListButton()
            super.show(step)
        }
    }

    /**
     * Prepares the UI for showing hint.
     *
     * 1. Waits until the view tree is about to be drawn.
     * 2. Scrolls to the first tier list.
     *
     * @param onReady Invoked when hint is ready to be shown.
     */
    private fun prepareForShowing(onReady: () -> Unit) {
        binding.root.doOnPreDraw {
            binding.listTierLists.scrollToPosition(0)
            onReady()
        }
    }

    /**
     * Shows "Add new list" floating action button.
     *
     * Since FAB visibility is controlled by the scroll behaviour of the coordinator layout, simple
     * show/hide doesn't work. Instead, [HideBottomViewOnScrollBehavior.slideUp] must be used.
     * Also, it's important to disable slide animation, because this function must be synchronous.
     */
    private fun showAddNewListButton() {
        val params = binding.btnAddNewList.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as HideBottomViewOnScrollBehavior
        behavior.slideUp(binding.btnAddNewList, false)
    }
}