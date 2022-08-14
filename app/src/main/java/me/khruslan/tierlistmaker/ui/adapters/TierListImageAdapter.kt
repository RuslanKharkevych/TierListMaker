package me.khruslan.tierlistmaker.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.ui.holders.TierListImageHolder
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION

/**
 * [RecyclerView.Adapter] implementation used for managing images in the tier list.
 * Can be used for both tier and backlog images.
 *
 * @property images list of images.
 * @property tierPosition position of the tier or [BACKLOG_TIER_POSITION] if it's backlog.
 * @property imageSize initial image size.
 * @property dragListener listener of tier list drag events.
 */
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

    /**
     * Updates the image size and notifies that data set was changed.
     *
     * @param imageSize new image size.
     */
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }

    /**
     * Creates unique tag for the view based on its position.
     * The tag is of [ImageDragData] type. It's needed to be able start dragging the image.
     *
     * @param itemPosition position of the image.
     * @param tierPosition position of the tier.
     * @return Created tag.
     */
    private fun createTag(itemPosition: Int, tierPosition: Int) =
        ImageDragData(
            image = images[itemPosition],
            itemPosition = itemPosition,
            tierPosition = tierPosition
        )
}
