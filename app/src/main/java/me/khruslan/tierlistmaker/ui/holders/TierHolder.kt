package me.khruslan.tierlistmaker.ui.holders

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.utils.view.FlexLayoutManager

/**
 * [RecyclerView.ViewHolder] implementation for the tier view.
 *
 * @property binding binding of the tier item view.
 * @property dragListener listener of tier list drag events.
 */
class TierHolder(
    private val binding: ItemTierBinding,
    private val dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.listImages.setOnDragListener(dragListener)
    }

    /**
     * Binds tier data to the [itemView].
     *
     * @param tier tier model.
     * @param imageSize size of the image.
     * @param tag drag data of the tier.
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
}