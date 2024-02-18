package me.khruslan.tierlistmaker.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable.Reorderable
import me.khruslan.tierlistmaker.presentation.holders.TierListPreviewHolder
import me.khruslan.tierlistmaker.util.swap

/**
 * Adapter that manages tier list previews.
 *
 * Allows to reorder and remove previews.
 *
 * @property tierListPreviews List of tier list previews.
 * @property onClick Item click listener.
 * @property onPreviewMoved Called when preview has been moved by user.
 * @property onPreviewSwiped Called when preview has been swiped by user.
 * @constructor Creates a new adapter instance.
 */
class TierListPreviewAdapter(
    private val tierListPreviews: MutableList<TierList.Preview>,
    private val onClick: (position: Int) -> Unit,
    private val onPreviewMoved: (from: Int, to: Int) -> Unit,
    private val onPreviewSwiped: (index: Int) -> Unit
) : RecyclerView.Adapter<TierListPreviewHolder>(), Reorderable {

    /**
     * Creates a new tier list preview holder instance.
     *
     * Called when recycler view needs a new holder to represent an item.
     *
     * @param parent The group into which the new view will be added after it is bound to an adapter
     * position.
     * @param viewType The type of the new view.
     * @return A new view holder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TierListPreviewHolder(
        binding = ItemTierListPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onClick = onClick
    )

    /**
     * Binds preview at given position.
     *
     * Called by recycler view to display the data at the specified position.
     *
     * @param holder The view holder which should be updated to represent the contents of the item
     * at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: TierListPreviewHolder, position: Int) {
        holder.bind(tierListPreviews[position])
    }

    /**
     * Returns the total number of previews.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = tierListPreviews.size

    /**
     * Swap previews and notifies that data set has changed.
     *
     * Also invokes [onPreviewMoved] callback to allow clients to add additional event handling
     * logic.
     *
     * @param fromPosition Initial position of the tier.
     * @param toPosition Target position of the tier.
     */
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        tierListPreviews.swap(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, tierListPreviews.size, Unit)
        onPreviewMoved(fromPosition, toPosition)
    }

    /**
     * Invokes [onPreviewSwiped] callback.
     *
     * User should be asked to confirm preview deletion. On confirmation call [removePreview] to
     * update the data set.
     *
     * @param position Position of the swiped preview.
     */
    override fun onItemSwiped(position: Int) {
        onPreviewSwiped(position)
    }

    /**
     * Removes preview by position and notifies that data set has changed.
     *
     * @param index Position of the preview to remove.
     */
    fun removePreview(index: Int) {
        tierListPreviews.removeAt(index)
        notifyItemRemoved(index)
    }
}
