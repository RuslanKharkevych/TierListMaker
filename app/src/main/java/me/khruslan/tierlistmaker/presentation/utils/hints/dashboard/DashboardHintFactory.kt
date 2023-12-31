package me.khruslan.tierlistmaker.presentation.utils.hints.dashboard

import android.content.Context
import com.google.android.material.R.attr.colorSecondary
import com.google.android.material.R.attr.colorSurface
import com.google.android.material.card.MaterialCardView
import com.takusemba.spotlight.shape.Circle
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.presentation.screens.home.DashboardFragment
import me.khruslan.tierlistmaker.presentation.utils.getMaterialColor
import me.khruslan.tierlistmaker.presentation.utils.hints.core.Hint
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.effects.CardRippleEffect
import me.khruslan.tierlistmaker.presentation.utils.hints.core.effects.CircularRippleEffect
import me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes.CardShape
import me.khruslan.tierlistmaker.presentation.views.HintOverlayView

/**
 * Factory that creates hints for [DashboardHintStep] entries.
 *
 * @property context activity context.
 * @property binding [DashboardFragment] binding.
 */
class DashboardHintFactory(
    private val context: Context,
    private val binding: FragmentDashboardBinding
) : HintFactory<DashboardHintStep>() {

    override fun create(step: DashboardHintStep): Hint {
        return when (step) {
            DashboardHintStep.AddNewTierList -> createAddNewTierListHint()
            DashboardHintStep.RemoveTierList -> createRemoveTierListHint()
            DashboardHintStep.ReorderTierLists -> createReorderTierListsHint()
        }
    }

    /**
     * Creates a hint that shows how to create a new tier list.
     *
     * @return created hint.
     */
    private fun createAddNewTierListHint(): Hint {
        val anchor = binding.btnAddNewList
        val shapeRadius = anchor.width / 2f

        return Hint(
            name = DashboardHintStep.AddNewTierList.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_add_new_tier_list)
            },
            anchor = anchor,
            shape = Circle(shapeRadius),
            effect = CircularRippleEffect(
                radius = shapeRadius,
                color = context.getMaterialColor(colorSecondary)
            )
        )
    }

    /**
     * Creates a hint that shows how to remove a tier list.
     *
     * @return created hint.
     */
    private fun createRemoveTierListHint(): Hint {
        return createTierListPreviewHint(
            name = DashboardHintStep.RemoveTierList.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_remove_tier_list)
                disableNextButton()
            }
        )
    }

    /**
     * Creates a hint that shows how to reorder tier lists.
     *
     * @return created hint.
     */
    private fun createReorderTierListsHint(): Hint {
        return createTierListPreviewHint(
            name = DashboardHintStep.ReorderTierLists.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_reorder_tier_lists)
                disablePreviousButton()
            }
        )
    }

    /**
     * A helper function that creates a hint with tier list card as an anchor.
     *
     * @param name the name of the hint.
     * @param overlay overlay view.
     * @return created hint.
     */
    private fun createTierListPreviewHint(name: String, overlay: HintOverlayView): Hint {
        val anchor = findFirstTierListPreview()

        return Hint(
            name = name,
            overlay = overlay,
            anchor = anchor,
            shape = anchor?.let { card ->
                CardShape(card)
            },
            effect = anchor?.let { card ->
                CardRippleEffect(
                    card = card,
                    color = context.getMaterialColor(colorSurface)
                )
            }
        )
    }

    /**
     * Finds the first tier list preview.
     *
     * @return card view or null if wasn't not found.
     */
    private fun findFirstTierListPreview(): MaterialCardView? {
        return binding.listTierLists.layoutManager?.findViewByPosition(0) as? MaterialCardView
    }
}