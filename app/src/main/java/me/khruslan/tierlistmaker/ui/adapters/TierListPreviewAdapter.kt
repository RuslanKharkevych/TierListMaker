package me.khruslan.tierlistmaker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.ui.holders.TierListPreviewHolder

/**
 * [RecyclerView.Adapter] implementation used for managing tier list previews.
 *
 * @property tierListPreviews list of tier list previews.
 * @property onClick item click listener.
 */
class TierListPreviewAdapter(
    private val tierListPreviews: List<TierList.Preview>,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<TierListPreviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TierListPreviewHolder(
        binding = ItemTierListPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onClick = { position -> onClick(position) }
    )

    override fun onBindViewHolder(holder: TierListPreviewHolder, position: Int) {
        holder.bind(tierListPreviews[position])
    }

    override fun getItemCount() = tierListPreviews.size
}
