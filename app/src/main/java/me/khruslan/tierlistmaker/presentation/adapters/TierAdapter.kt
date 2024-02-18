package me.khruslan.tierlistmaker.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.databinding.ItemTierBinding
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable.Reorderable
import me.khruslan.tierlistmaker.presentation.holders.TierHolder
import me.khruslan.tierlistmaker.util.swap
import timber.log.Timber

/**
 * Adapter that manages tiers in the tier list.
 *
 * Allows to reorder and remove tiers.
 *
 * @property tiers List of tiers.
 * @property dragListener Listener of tier list drag events.
 * @property imageSize Initial image size.
 * @property onTierRemoved Called when tier has been swiped by user.
 * @constructor Creates a new adapter instance.
 */
class TierAdapter(
    private val tiers: MutableList<Tier>,
    private val dragListener: View.OnDragListener,
    private var imageSize: Int,
    private val onTierRemoved: (tier: Tier) -> Unit
) : RecyclerView.Adapter<TierHolder>(), Reorderable {

    /**
     * Creates a new tier holder instance.
     *
     * Called when recycler view needs a new holder to represent an item.
     *
     * @param parent The group into which the new view will be added after it is bound to an adapter
     * position.
     * @param viewType The type of the new view.
     * @return A new view holder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TierHolder(
            binding = ItemTierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            dragListener = dragListener
        )

    /**
     * Binds tier at given position.
     *
     * Called by recycler view to display the data at the specified position.
     *
     * @param holder The view holder which should be updated to represent the contents of the item
     * at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: TierHolder, position: Int) {
        holder.bind(tier = tiers[position], imageSize = imageSize, tag = TierDragData(position))
    }

    /**
     * Returns the total number of tiers.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = tiers.size

    /**
     * Swap tiers and notifies that data set has changed.
     *
     * Note that tier styles are not swapped.
     *
     * @param fromPosition Initial position of the tier.
     * @param toPosition Target position of the tier.
     */
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Timber.i("Swapped tiers at indices $fromPosition and $toPosition")
        tiers.swap(fromPosition, toPosition)
        swapTierStyles(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, tiers.size, Unit)
        Timber.i("Updated tiers: $tiers")
    }

    /**
     * Removes tier at given position and notifies that data set has changed.
     *
     * Invokes [onTierRemoved] callback to allow restoring removed tier.
     *
     * @param position Position of the swiped tier.
     */
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
     * Updates the image size and notifies that data set has changed.
     *
     * @param imageSize New image size.
     */
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }

    /**
     * Swaps styles of tiers without altering other contents.
     *
     * @param firstTierIndex Index of the first tier.
     * @param secondTierIndex Index of the second tier.
     */
    private fun swapTierStyles(firstTierIndex: Int, secondTierIndex: Int) {
        val temp = tiers[firstTierIndex].style
        tiers[firstTierIndex].style = tiers[secondTierIndex].style
        tiers[secondTierIndex].style = temp
    }
}
