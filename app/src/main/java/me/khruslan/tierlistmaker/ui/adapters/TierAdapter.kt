package me.khruslan.tierlistmaker.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.ui.adapters.reorderable.Reorderable
import me.khruslan.tierlistmaker.ui.holders.TierHolder
import me.khruslan.tierlistmaker.utils.extensions.swap

/**
 * [RecyclerView.Adapter] implementation used for managing tiers in the tier list.
 *
 * @property tiers list of tiers.
 * @property dragListener listener of tier list drag events.
 * @property imageSize initial image size.
 */
class TierAdapter(
    private val tiers: MutableList<Tier>,
    private val dragListener: View.OnDragListener,
    private var imageSize: Int
) : RecyclerView.Adapter<TierHolder>(), Reorderable {

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

    /**
     * Updates the image size and notifies that data set was changed.
     *
     * @param imageSize new image size.
     */
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }
}
