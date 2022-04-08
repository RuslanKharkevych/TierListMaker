package me.khruslan.tierlistmaker.ui.holders

import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.tierlist.Image
import me.khruslan.tierlistmaker.data.tierlist.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.StorageImage
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
        if (view == null) {
            Timber.e("onLongClick: view is null")
            return false
        }

        val data = view.tag
        if (data !is ImageDragData) {
            Timber.e("onLongClick: view has incompatible tag ($data)")
            return false
        }

        val result = view.startDragCompat(data)
        logStartDragResult(view, data, result)

        return true
    }

    /**
     * Logs the result of the [View.startDragCompat] function invocation.
     *
     * @param view view on which the long click was performed.
     * @param data tag of the view.
     * @param result whether the drag was started successfully.
     */
    private fun logStartDragResult(
        view: View,
        data: ImageDragData,
        result: Boolean
    ) {
        val desc = "view = $view, data = $data"

        if (result) {
            Timber.i("onTouch: successfully started drag ($desc)")
        } else {
            Timber.e("onTouch: unable to start drag ($desc)")
        }
    }
}