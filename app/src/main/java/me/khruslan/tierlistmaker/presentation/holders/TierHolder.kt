package me.khruslan.tierlistmaker.presentation.holders

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.presentation.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.FlexLayoutManager

/**
 * Holder of the tier view.
 *
 * The tier view includes header and nested recycler view with images.
 *
 * @property binding Binding of the tier item view.
 * @property dragListener Listener of tier list drag events.
 * @constructor Creates a new holder instance.
 */
class TierHolder(
    private val binding: ItemTierBinding,
    private val dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setOnDragListener()
    }

    /**
     * Binds tier data to the [itemView].
     *
     * Updates tier style and populates images list view.
     *
     * @param tier Tier model.
     * @param imageSize Size of the image.
     * @param tag Drag data of the tier.
     */
    fun bind(tier: Tier, imageSize: Int, tag: TierDragData) {
        binding.listImages.tag = tag

        with(binding.textTier) {
            text = tier.style.title
            background = ColorDrawable(tier.style.color)
            updateLayoutParams { width = imageSize }
        }

        with(binding.listImages) {
            minimumHeight = imageSize
            adapter = TierListImageAdapter(
                images = tier.images,
                tierPosition = adapterPosition,
                imageSize = imageSize,
                dragListener = dragListener
            )
            layoutManager = FlexLayoutManager(binding.root.context)
        }
    }

    /**
     * Registers a drag event listener callback object for the tier images view.
     *
     * This allows dropping images into a tier.
     */
    private fun setOnDragListener() {
        binding.listImages.setOnDragListener(dragListener)
    }
}