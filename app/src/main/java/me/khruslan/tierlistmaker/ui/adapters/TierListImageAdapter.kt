package me.khruslan.tierlistmaker.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.ui.holders.TierListImageHolder
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION

class TierListImageAdapter(
    imageUrls: List<String?>,
    private val tierPosition: Int,
    private var imageSize: Int,
    private val dragListener: View.OnDragListener
) : RecyclerView.Adapter<TierListImageHolder>() {

    private var imageUrls = imageUrls.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TierListImageHolder(
            imageView = ImageView(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(imageSize, imageSize)
            },
            dragListener = dragListener
        )

    override fun onBindViewHolder(holder: TierListImageHolder, position: Int) {
        holder.bind(
            imageUrl = imageUrls[position],
            imageSize = imageSize,
            tag = createTag(position, tierPosition)
        )
    }

    override fun getItemCount() = imageUrls.size

    fun insertImage(url: String? = null, position: Int = imageUrls.size) {
        imageUrls.add(position, url)
        notifyItemInserted(position)
        updateDataSetIfNeeded()
    }

    fun removeImage(position: Int) {
        imageUrls.removeAt(position)
        notifyItemRemoved(position)
        updateDataSetIfNeeded()
    }

    fun removeLastImage() = removeImage(imageUrls.lastIndex)

    fun updateImage(imageUrl: String?, position: Int) {
        imageUrls[position] = imageUrl
        notifyItemChanged(position)
    }

    fun updateLastImage(imageUrl: String?) = updateImage(imageUrl, imageUrls.lastIndex)

    @SuppressLint("NotifyDataSetChanged")
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }

    private fun createTag(itemPosition: Int, tierPosition: Int) =
        ImageDragData(
            imageUrl = imageUrls[itemPosition],
            itemPosition = itemPosition,
            tierPosition = tierPosition
        )

    private fun updateDataSetIfNeeded() {
        if (tierPosition == BACKLOG_TIER_POSITION) {
            notifyItemRangeChanged(0, imageUrls.size, Unit)
        }
    }
}
