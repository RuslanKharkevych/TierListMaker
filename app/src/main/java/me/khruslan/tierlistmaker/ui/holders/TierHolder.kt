package me.khruslan.tierlistmaker.ui.holders

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.utils.view.FlexLayoutManager

class TierHolder(
    private val binding: ItemTierBinding,
    private val dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.listImages.setOnDragListener(dragListener)
    }

    fun bind(tier: Tier, imageSize: Int, tag: TierDragData) {
        binding.listImages.tag = tag

        with(binding.textTier) {
            text = tier.title
            background = ColorDrawable(tier.color)
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