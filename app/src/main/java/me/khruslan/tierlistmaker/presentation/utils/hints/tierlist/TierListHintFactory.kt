package me.khruslan.tierlistmaker.presentation.utils.hints.tierlist

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.spotlight.shape.RoundedRectangle
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListFragment
import me.khruslan.tierlistmaker.presentation.utils.hints.core.Hint
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintFactory
import me.khruslan.tierlistmaker.presentation.utils.hints.core.effects.RectangularRippleEffect
import me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes.SquareShape
import me.khruslan.tierlistmaker.presentation.views.HintOverlayView

/**
 * Factory that creates hints for [TierListHintStep] entries.
 *
 * @property context Activity context.
 * @property binding [TierListFragment] binding.
 * @constructor Creates a new hint factory.
 */
class TierListHintFactory(
    private val context: Context,
    private val binding: FragmentTierListBinding
) : HintFactory<TierListHintStep>() {

    /**
     * Creates a tier list hint for the provided step.
     *
     * When adding or removing steps, make sure to update overlay views to show/hide previous and
     * next buttons depending on the step index.
     *
     * @param step Step of the hint.
     * @return Created hint.
     */
    override fun create(step: TierListHintStep): Hint {
        return when (step) {
            TierListHintStep.ReorderTiers -> createReorderTiersHint()
            TierListHintStep.RemoveImage -> createRemoveImageHint()
            TierListHintStep.RemoveTier -> createRemoveTierHint()
        }
    }

    /**
     * Creates a hint that shows how to reorder tiers.
     *
     * Anchored to the header of the first tier. This is the first hint, that's why the previous
     * button is disabled.
     *
     * @return Created hint.
     */
    private fun createReorderTiersHint(): Hint {
        return createTierListHeaderHint(
            name = TierListHintStep.ReorderTiers.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_reorder_tiers)
                disablePreviousButton()
            }
        )
    }

    /**
     * Creates a hint that shows how to remove an image.
     *
     * Anchored to the first tier list image. Previous and next buttons are enabled. If no tier list
     * images are found, the hint will be created without the anchor.
     *
     * @return Created hint.
     */
    private fun createRemoveImageHint(): Hint {
        val anchor = findFirstTierListImage()

        return Hint(
            name = TierListHintStep.RemoveImage.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_remove_image)
            },
            anchor = anchor,
            shape = anchor?.let { view ->
                SquareShape(view.width)
            },
            effect = anchor?.let { view ->
                RectangularRippleEffect(
                    width = view.width,
                    height = view.height,
                    offset = context.resources.getDimensionPixelOffset(R.dimen.tier_list_image_ripple_offset),
                    color = ContextCompat.getColor(context, R.color.purple200)
                )
            }
        )
    }

    /**
     * Creates a hint that shows how to remove a tier.
     *
     * Anchored to the header of the first tier. This is the last hint, that's why the next button
     * is disabled.
     *
     * @return Created hint.
     */
    private fun createRemoveTierHint(): Hint {
        return createTierListHeaderHint(
            name = TierListHintStep.RemoveTier.name,
            overlay = HintOverlayView(context).apply {
                setHintText(R.string.hint_remove_tier)
                disableNextButton()
            }
        )
    }

    /**
     * A helper function that creates a hint with the header of the first tier as an anchor.
     *
     * If no tiers are found, the hint will be created without the anchor.
     *
     * @param name The name of the hint.
     * @param overlay Overlay view.
     * @return Created hint.
     */
    private fun createTierListHeaderHint(name: String, overlay: HintOverlayView): Hint {
        val anchor = findFirstTierHeader()

        return Hint(
            name = name,
            overlay = overlay,
            anchor = anchor,
            shape = anchor?.let { view ->
                val rect = Rect()
                anchor.getGlobalVisibleRect(rect)
                val height = rect.height()

                RoundedRectangle(
                    height = height.toFloat(),
                    width = view.width.toFloat(),
                    radius = 0f
                )
            },
            effect = anchor?.let { view ->
                val rect = Rect()
                anchor.getGlobalVisibleRect(rect)
                val height = rect.height()

                RectangularRippleEffect(
                    width = view.width,
                    height = height,
                    offset = context.resources.getDimensionPixelOffset(R.dimen.tier_list_header_ripple_offset),
                    color = (view.background as ColorDrawable).color
                )
            }
        )
    }

    /**
     * Finds a header view of the first tier.
     *
     * This function requires a layout manager to be attached to the tiers recycler view.
     *
     * @return Header view or null if there are no tiers.
     */
    private fun findFirstTierHeader(): View? {
        val tierView = binding.listTiers.layoutManager?.findViewByPosition(0)
        return tierView?.findViewById(R.id.text_tier)
    }

    /**
     * Finds the first tier list image.
     *
     * Loops through tiers from top to bottom (including backlog as a last resort) and checks if it
     * has at least image. If tier is not empty, returns the first image. This function requires
     * layout managers to be attached to recycler views of tiers and images.
     *
     * @return Image view or null if there are no images.
     */
    private fun findFirstTierListImage(): View? {
        val tiersLayoutManager = binding.listTiers.layoutManager
        for (index in 0 until (tiersLayoutManager?.itemCount ?: 0)) {
            val tierView = tiersLayoutManager?.findViewByPosition(index)
            val imagesListView = tierView?.findViewById<RecyclerView>(R.id.list_images)
            val imageView = imagesListView?.layoutManager?.findViewByPosition(0)
            if (imageView != null) return imageView
        }
        return binding.listBacklogImages.layoutManager?.findViewByPosition(0)
    }
}