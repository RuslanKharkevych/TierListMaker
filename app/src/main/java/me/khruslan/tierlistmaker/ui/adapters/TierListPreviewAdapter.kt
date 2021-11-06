package me.khruslan.tierlistmaker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.TierListPreview
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.utils.extensions.hide

class TierListPreviewAdapter(private val tierListPreviews: List<TierListPreview>)
    : RecyclerView.Adapter<TierListPreviewHolder>() {

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

class TierListPreviewHolder(private val binding: ItemTierListPreviewBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(preview: TierListPreview) {
        with(binding) {
            textTitle.text = preview.title

            imgFirst.bindImage(preview.imageUrls.getOrNull(0))
            imgSecond.bindImage(preview.imageUrls.getOrNull(1))
            imgThird.bindImage(preview.imageUrls.getOrNull(2))
        }
    }

    private fun ImageView.bindImage(imageUrl: String?) {
        if (imageUrl == null) hide() else loadImage(imageUrl)
    }

    private fun ImageView.loadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(this)
    }
}