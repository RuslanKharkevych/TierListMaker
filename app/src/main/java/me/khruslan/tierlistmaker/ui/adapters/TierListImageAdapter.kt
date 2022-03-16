package me.khruslan.tierlistmaker.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.ui.holders.TierListImageHolder
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION

class TierListImageAdapter(
    images: List<Image>,
    private val tierPosition: Int,
    private var imageSize: Int,
    private val dragListener: View.OnDragListener
) : RecyclerView.Adapter<TierListImageHolder>() {

    private var images = images.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TierListImageHolder(
            imageView = ImageView(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(imageSize, imageSize)
            },
            dragListener = dragListener
        )

    override fun onBindViewHolder(holder: TierListImageHolder, position: Int) {
        holder.bind(
            image = images[position],
            imageSize = imageSize,
            tag = createTag(position, tierPosition)
        )
    }

    override fun getItemCount() = images.size

    fun insertImage(image: Image, position: Int = images.size) {
        images.add(position, image)
        notifyItemInserted(position)
        updateDataSetIfNeeded()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun insertImages(images: List<Image>) {
        this.images.addAll(0, images)
        notifyDataSetChanged() // TODO: Investigate a better solution
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeImage(position: Int) {
        images.removeAt(position)
        notifyDataSetChanged() // TODO: Investigate a better solution
    }

    fun removeLastImage() = removeImage(images.lastIndex)

    fun updateImage(image: Image, position: Int) {
        images[position] = image
        notifyItemChanged(position)
    }

    fun updateLastImage(image: Image) = updateImage(image, images.lastIndex)

    @SuppressLint("NotifyDataSetChanged")
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }

    private fun createTag(itemPosition: Int, tierPosition: Int) =
        ImageDragData(
            image = images[itemPosition],
            itemPosition = itemPosition,
            tierPosition = tierPosition
        )

    private fun updateDataSetIfNeeded() {
        if (tierPosition == BACKLOG_TIER_POSITION) {
            notifyItemRangeChanged(0, images.size, Unit)
        }
    }
}
