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
 * [RecyclerView.Adapter] implementation used for managing tier list previews.
 *
 * @property tierListPreviews list of tier list previews.
 * @property onClick item click listener.
 */
class TierListPreviewAdapter(
    private val tierListPreviews: MutableList<TierList.Preview>,
    private val onClick: (position: Int) -> Unit,
    private val onTierListMoved: (from: Int, to: Int) -> Unit,
    private val onTierListSwiped: (index: Int) -> Unit
) : RecyclerView.Adapter<TierListPreviewHolder>(), Reorderable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TierListPreviewHolder(
        binding = ItemTierListPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onClick = onClick
    )

    override fun onBindViewHolder(holder: TierListPreviewHolder, position: Int) {
        holder.bind(tierListPreviews[position])
    }

    override fun getItemCount() = tierListPreviews.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        tierListPreviews.swap(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, tierListPreviews.size, Unit)
        onTierListMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        onTierListSwiped(position)
    }

    /**
     * Removes preview by position and updates the data set accordingly.
     *
     * @param index position of the preview to remove.
     */
    fun removePreview(index: Int) {
        tierListPreviews.removeAt(index)
        notifyItemRemoved(index)
    }
}
