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
import me.khruslan.tierlistmaker.utils.swap
import timber.log.Timber

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
    private var imageSize: Int,
    private val onTierRemoved: (tier: Tier) -> Unit
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
        Timber.i("Swapped tiers at indices $fromPosition and $toPosition")
        tiers.swap(fromPosition, toPosition)
        swapTierStyles(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, tiers.size, Unit)
        Timber.i("Updated tiers: $tiers")
    }

    override fun onItemSwiped(position: Int) {
        Timber.i("Tier removed by user at position $position")
        val tier = tiers[position]
        tiers.remove(tier)
        notifyItemRemoved(position)
        notifyItemRangeChanged(0, tiers.size, Unit)
        Timber.i("Updated tiers: $tiers")
        onTierRemoved(tier)
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

    /**
     * Swaps styles of tiers without altering other contents.
     *
     * @param firstTierIndex index of first tier.
     * @param secondTierIndex index of second tier.
     */
    private fun swapTierStyles(firstTierIndex: Int, secondTierIndex: Int) {
        val temp = tiers[firstTierIndex].style
        tiers[firstTierIndex].style = tiers[secondTierIndex].style
        tiers[secondTierIndex].style = temp
    }
}
