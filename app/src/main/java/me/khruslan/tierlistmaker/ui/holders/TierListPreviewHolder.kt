package me.khruslan.tierlistmaker.ui.holders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.utils.loadTierListImage

/**
 * [RecyclerView.ViewHolder] implementation for the tier list preview.
 *
 * @property binding binding of the tier list preview.
 * @property onClick item click listener.
 */
class TierListPreviewHolder(
    private val binding: ItemTierListPreviewBinding,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    /**
     * Binds [TierList.Preview] to the [itemView].
     *
     * @param preview preview of the tier list.
     */
    fun bind(preview: TierList.Preview) {
        with(binding) {
            textTitle.text = preview.title

            imgFirst.bindImage(preview.images.getOrNull(0))
            imgSecond.bindImage(preview.images.getOrNull(1))
            imgThird.bindImage(preview.images.getOrNull(2))
        }
    }

    /**
     * Binds [Image] to the [ImageView].
     *
     * @param image image or null.
     */
    private fun ImageView.bindImage(image: Image?) {
        when(image) {
            null -> setImageResource(R.drawable.ic_image)
            is StorageImage -> loadTierListImage(image.filePath)
            is ResourceImage -> setImageResource(image.resId)
        }
    }
}