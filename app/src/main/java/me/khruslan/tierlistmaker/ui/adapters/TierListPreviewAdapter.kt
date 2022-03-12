package me.khruslan.tierlistmaker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.tierlist.TierListPreview
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.ui.holders.TierListPreviewHolder

class TierListPreviewAdapter(private val tierListPreviews: List<TierListPreview>) :
    RecyclerView.Adapter<TierListPreviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TierListPreviewHolder(
        ItemTierListPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TierListPreviewHolder, position: Int) {
        holder.bind(tierListPreviews[position])
    }

    override fun getItemCount() = tierListPreviews.size
}
