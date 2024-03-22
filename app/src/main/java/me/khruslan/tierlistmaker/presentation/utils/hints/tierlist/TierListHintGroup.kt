package me.khruslan.tierlistmaker.presentation.utils.hints.tierlist

import android.app.Activity
import androidx.core.view.doOnPreDraw
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListFragment
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintGroup

/**
 * A group of hints for [TierListFragment].
 *
 * @property activity An activity that hosts the fragment.
 * @property binding Binding of the fragment.
 * @constructor Creates a new hint group.
 */
class TierListHintGroup(
    private val activity: Activity,
    private val binding: FragmentTierListBinding
) : HintGroup<TierListHintStep>(
    name = NAME,
    activity = activity,
    steps = TierListHintStep.entries
) {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * The debug name of this hint group.
         */
        private const val NAME = "TierListHintGroup"
    }

    /**
     * Provides tier list hint factory.
     *
     * @return Provided factory.
     */
    override fun hintFactory(): HintFactory<TierListHintStep> {
        return TierListHintFactory(activity, binding)
    }

    /**
     * Shows the hint group starting with the given step.
     *
     * Note that step is shown when the view tree is about to be drawn.
     *
     * @param step Step to be shown.
     */
    override fun show(step: TierListHintStep) {
        binding.root.doOnPreDraw {
            super.show(step)
        }
    }
}