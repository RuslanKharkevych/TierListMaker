package me.khruslan.tierlistmaker.ui.holders

import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.utils.extensions.loadTierListImage
import me.khruslan.tierlistmaker.utils.extensions.startDragCompat
import timber.log.Timber

/**
 * [RecyclerView.ViewHolder] implementation for the tier list image.
 *
 * @property imageView view of the image.
 * @param dragListener listener of tier list drag events.
 */
class TierListImageHolder(
    private val imageView: ImageView,
    dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(imageView), View.OnLongClickListener {

    init {
        itemView.setOnLongClickListener(this)
        itemView.setOnDragListener(dragListener)
    }

    /**
     * Binds image data to the [itemView].
     *
     * @param image image model.
     * @param imageSize size of the image.
     * @param tag drag data of the image.
     */
    fun bind(image: Image, imageSize: Int, tag: ImageDragData) {
        with(imageView) {
            this.tag = tag
            updateLayoutParams {
                height = imageSize
                width = imageSize
            }

            when (image) {
                is StorageImage -> loadTierListImage(image.filePath)
                is ResourceImage -> setImageResource(image.resId)
            }
        }
    }

    override fun onLongClick(view: View?): Boolean {
        return try {
            startDrag(view)
            true
        } catch (e: Exception) {
            Timber.e(e, "Unable to start drag")
            false
        }
    }

    /**
     * Attempts to start drag.
     *
     * @param view view that should be dragged.
     */
    private fun startDrag(view: View?) {
        val data = view?.tag

        when {
            data is ImageDragData -> startDrag(view, data)
            view == null -> throw TierListImageException("onLongClick: view is null")
            else -> throw TierListImageException("onLongClick: view has incompatible tag ($data)")
        }
    }

    /**
     * Starts drag.
     *
     * @param view view that should be dragged.
     * @param data payload of the dragged item.
     */
    private fun startDrag(view: View, data: ImageDragData) {
        val result = view.startDragCompat(data)
        val resultDesc = "data = $data"

        if (result) {
            Timber.i("onTouch: successfully started drag ($resultDesc)")
        } else {
            throw TierListImageException("onTouch: unable to start drag ($resultDesc)")
        }
    }

    /**
     * Exception that can happen when tier list image can't be dragged successfully.
     *
     * @param message exception message.
     */
    private class TierListImageException(message: String) : Exception(message)
}