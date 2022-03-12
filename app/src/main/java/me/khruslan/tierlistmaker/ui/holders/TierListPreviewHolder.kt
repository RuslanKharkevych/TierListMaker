package me.khruslan.tierlistmaker.ui.holders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.tierlist.TierListPreview
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.utils.extensions.hide
import me.khruslan.tierlistmaker.utils.extensions.loadTierListImage

class TierListPreviewHolder(private val binding: ItemTierListPreviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(preview: TierListPreview) {
        with(binding) {
            textTitle.text = preview.title

            imgFirst.bindImage(preview.imageUrls.getOrNull(0))
            imgSecond.bindImage(preview.imageUrls.getOrNull(1))
            imgThird.bindImage(preview.imageUrls.getOrNull(2))
        }
    }

    private fun ImageView.bindImage(imageUrl: String?) {
        if (imageUrl == null) hide() else loadTierListImage(imageUrl)
    }
}