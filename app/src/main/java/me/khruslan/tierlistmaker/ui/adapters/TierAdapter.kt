package me.khruslan.tierlistmaker.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.ui.adapters.reorderable.Reorderable
import me.khruslan.tierlistmaker.ui.holders.TierHolder
import me.khruslan.tierlistmaker.utils.extensions.swap

class TierAdapter(
    tiers: List<Tier>,
    private val dragListener: View.OnDragListener,
    private var imageSize: Int
) : RecyclerView.Adapter<TierHolder>(), Reorderable {

    private val tiers = tiers.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TierHolder(
            binding = ItemTierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            dragListener = dragListener
        )

    override fun onBindViewHolder(holder: TierHolder, position: Int) {
        holder.bind(tier = tiers[position], imageSize = imageSize, tag = TierDragData(position))
    }

    override fun getItemCount() = tiers.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        tiers.swap(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, tiers.size, Unit)
    }

    override fun onItemSwiped(position: Int) {
        tiers.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(0, tiers.size, Unit)
    }

    fun removeImage(tierPosition: Int, itemPosition: Int) {
        tiers[tierPosition].images.removeAt(itemPosition)
        notifyItemChanged(tierPosition)
    }

    fun removeLastImage(tierPosition: Int) = removeImage(
        tierPosition = tierPosition,
        itemPosition = tiers[tierPosition].images.lastIndex
    )

    fun updateImage(image: Image, tierPosition: Int, itemPosition: Int) {
        tiers[tierPosition].images[itemPosition] = image
        notifyItemChanged(tierPosition)
    }

    fun updateLastImage(image: Image, tierPosition: Int) = updateImage(
        image = image,
        tierPosition = tierPosition,
        itemPosition = tiers[tierPosition].images.lastIndex
    )

    fun insertImage(
        image: Image,
        tierPosition: Int,
        itemPosition: Int = tiers[tierPosition].images.size
    ) {
        tiers[tierPosition].images.add(itemPosition, image)
        notifyItemChanged(tierPosition)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }
}
