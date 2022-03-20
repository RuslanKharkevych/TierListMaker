package me.khruslan.tierlistmaker.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.ui.holders.TierListImageHolder

class TierListImageAdapter(
    private val images: MutableList<Image>,
    private val tierPosition: Int,
    private var imageSize: Int,
    private val dragListener: View.OnDragListener
) : RecyclerView.Adapter<TierListImageHolder>() {

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
}
