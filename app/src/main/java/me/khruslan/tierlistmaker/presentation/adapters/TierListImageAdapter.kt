package me.khruslan.tierlistmaker.presentation.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.presentation.holders.TierListImageHolder
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION

/**
 * Adapter that manages images in tier or backlog.
 *
 * Allows to drag and drop images.
 *
 * @property images List of images.
 * @property tierPosition Position of the tier or [BACKLOG_TIER_POSITION] if it's backlog.
 * @property imageSize Initial image size.
 * @property dragListener Listener of tier list drag events.
 * @constructor Creates a new adapter instance.
 */
class TierListImageAdapter(
    private val images: MutableList<Image>,
    private val tierPosition: Int,
    private var imageSize: Int,
    private val dragListener: View.OnDragListener
) : RecyclerView.Adapter<TierListImageHolder>() {

    /**
     * Creates a new tier list image holder instance.
     *
     * Called when recycler view needs a new holder to represent an item.
     *
     * @param parent The group into which the new view will be added after it is bound to an adapter
     * position.
     * @param viewType The type of the new view.
     * @return A new view holder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TierListImageHolder(
            imageView = ImageView(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(imageSize, imageSize)
            },
            dragListener = dragListener
        )

    /**
     * Binds image at given position.
     *
     * Called by recycler view to display the data at the specified position.
     *
     * @param holder The view holder which should be updated to represent the contents of the item
     * at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: TierListImageHolder, position: Int) {
        holder.bind(
            image = images[position],
            imageSize = imageSize,
            tag = createTag(position, tierPosition)
        )
    }

    /**
     * Returns the total number of images.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = images.size

    /**
     * Updates the image size and notifies that data set has changed.
     *
     * @param imageSize New image size.
     */
    fun updateImageSize(imageSize: Int) {
        this.imageSize = imageSize
        notifyDataSetChanged()
    }

    /**
     * Creates unique tag for the view based on its position.
     *
     * It's needed to be able to drag the image.
     *
     * @param itemPosition Position of the image.
     * @param tierPosition Position of the tier.
     * @return Created tag.
     */
    private fun createTag(itemPosition: Int, tierPosition: Int) =
        ImageDragData(
            image = images[itemPosition],
            itemPosition = itemPosition,
            tierPosition = tierPosition
        )
}
