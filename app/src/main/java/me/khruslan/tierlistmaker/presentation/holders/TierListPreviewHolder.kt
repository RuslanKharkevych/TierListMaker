package me.khruslan.tierlistmaker.presentation.holders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ItemTierListPreviewBinding
import me.khruslan.tierlistmaker.presentation.utils.loadTierListImage
import me.khruslan.tierlistmaker.presentation.utils.setOnThrottledClickListener

/**
 * View holder of the tier list preview.
 *
 * Tier list preview is a card with a title and three images.
 *
 * @property binding Binding of the tier list preview.
 * @property onClick Item click listener.
 * @constructor Creates a new holder instance.
 */
class TierListPreviewHolder(
    private val binding: ItemTierListPreviewBinding,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setOnClickListener()
    }

    /**
     * Binds tier list preview to the item view.
     *
     * Updates title and loads images.
     *
     * @param preview Preview of the tier list.
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
     * Binds image to the image view.
     *
     * If image is null, placeholder is used.
     *
     * @param image Image or null.
     * @receiver Image view to which image is set.
     */
    private fun ImageView.bindImage(image: Image?) {
        when (image) {
            null -> setImageResource(R.drawable.ic_image)
            is StorageImage -> loadTierListImage(image.filePath)
            is ResourceImage -> setImageResource(image.resId)
        }
    }

    /**
     * Initializes the item click listener.
     *
     * The whole preview card is clickable. The clicks are throttled.
     */
    private fun setOnClickListener() {
        binding.root.setOnThrottledClickListener {
            onClick(adapterPosition)
        }
    }
}