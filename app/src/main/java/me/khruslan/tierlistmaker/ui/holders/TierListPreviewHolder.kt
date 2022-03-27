package me.khruslan.tierlistmaker.ui.holders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.data.tierlist.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.StorageImage
import me.khruslan.tierlistmaker.data.tierlist.TierListPreview
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.utils.extensions.invisible
import me.khruslan.tierlistmaker.utils.extensions.loadTierListImage

class TierListPreviewHolder(
    private val binding: ItemTierListPreviewBinding,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bind(preview: TierListPreview) {
        with(binding) {
            textTitle.text = preview.title

            imgFirst.bindImage(preview.images.getOrNull(0))
            imgSecond.bindImage(preview.images.getOrNull(1))
            imgThird.bindImage(preview.images.getOrNull(2))
        }
    }

    private fun ImageView.bindImage(image: Image?) {
        when(image) {
            null -> invisible()
            is StorageImage -> loadTierListImage(image.filePath)
            is ResourceImage -> setImageResource(image.resId)
        }
    }
}