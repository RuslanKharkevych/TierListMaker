package me.khruslan.tierlistmaker.presentation.utils.hints.collection

import android.content.Context
import com.google.android.material.R.attr.colorSecondary
import com.google.android.material.R.attr.colorSurface
import com.google.android.material.card.MaterialCardView
import com.takusemba.spotlight.shape.Circle
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.FragmentCollectionBinding
import me.khruslan.tierlistmaker.presentation.screens.home.CollectionFragment
import me.khruslan.tierlistmaker.presentation.utils.getMaterialColor
import me.khruslan.tierlistmaker.presentation.utils.hints.core.Hint
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.effects.CardRippleEffect
import me.khruslan.tierlistmaker.presentation.utils.hints.core.effects.CircularRippleEffect
import me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes.CardShape
import me.khruslan.tierlistmaker.presentation.views.HintOverlayView

/**
 * Factory that creates hints for [CollectionHintStep] entries.
 *
 * @property context Activity context.
 * @property binding [CollectionFragment] binding.
 * @constructor Creates a new hint factory.
 */
class CollectionHintFactory(
    private val context: Context,
    private val binding: FragmentCollectionBinding
) : HintFactory<CollectionHintStep>() {

    /**
     * Creates a collection hint for the provided step.
     *
     * When adding or removing steps, make sure to update overlay views to show/hide previous and
     * next buttons depending on the step index.
     *
     * @param step Step of the hint.
     * @return Created hint.
     */
    override fun create(step: CollectionHintStep): Hint {
        return when (step) {
            CollectionHintStep.AddNewTierList -> createAddNewTierListHint()
            CollectionHintStep.RemoveTierList -> createRemoveTierListHint()
            CollectionHintStep.ReorderTierLists -> createReorderTierListsHint()
        }
    }

    /**
     * Creates a hint that shows how to create a new tier list.
     *
     * Anchored to the "Add new list" button. Previous and next buttons are enabled.
     *
     * @return Created hint.
     */
    private fun createAddNewTierListHint(): Hint {
        val anchor = binding.btnAddNewList
        val shapeRadius = anchor.width / 2f

        return Hint(
            name = CollectionHintStep.AddNewTierList.name,
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
     * Anchored to the tier list card. This is the last hint, that's why the next button is
     * disabled.
     *
     * @return Created hint.
     */
    private fun createRemoveTierListHint(): Hint {
        return createTierListPreviewHint(
            name = CollectionHintStep.RemoveTierList.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_remove_tier_list)
                disableNextButton()
            }
        )
    }

    /**
     * Creates a hint that shows how to reorder tier lists.
     *
     * Anchored to the tier list card. This is the first hint, that's why the previous button is
     * disabled.
     *
     * @return Created hint.
     */
    private fun createReorderTierListsHint(): Hint {
        return createTierListPreviewHint(
            name = CollectionHintStep.ReorderTierLists.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_reorder_tier_lists)
                disablePreviousButton()
            }
        )
    }

    /**
     * A helper function that creates a hint with tier list card as an anchor.
     *
     * If tier list cart is not found, the hint will be created without the anchor.
     *
     * @param name The name of the hint.
     * @param overlay Overlay view.
     * @return Created hint.
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
     * Card view can be found only if recycler view has layout manager set up and it's not empty.
     *
     * @return Card view or null if wasn't not found.
     */
    private fun findFirstTierListPreview(): MaterialCardView? {
        return binding.listTierLists.layoutManager?.findViewByPosition(0) as? MaterialCardView
    }
}