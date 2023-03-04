package me.khruslan.tierlistmaker.ui.holders

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.utils.loadTierListImage
import me.khruslan.tierlistmaker.utils.startDragCompat
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
) : RecyclerView.ViewHolder(imageView), View.OnTouchListener {

    /**
     * Companion object of [TierListImageHolder] used to store constants.
     */
    companion object {
        private const val START_DRAG_DELTA_MILLIS = 200L
    }

    /**
     * Timestamp of when the latest successful drag started.
     */
    private var startDragTimestamp = 0L

    init {
        itemView.setOnTouchListener(this)
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
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
            startDragTimestamp = System.currentTimeMillis()
            Timber.i("onTouch: successfully started drag ($resultDesc)")
        } else if (System.currentTimeMillis() - startDragTimestamp > START_DRAG_DELTA_MILLIS) {
            // Workaround to avoid logging error when onTouch is called twice
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