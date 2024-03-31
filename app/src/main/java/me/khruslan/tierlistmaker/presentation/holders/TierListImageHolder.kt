package me.khruslan.tierlistmaker.presentation.holders

import android.annotation.SuppressLint
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.presentation.utils.loadTierListImage
import timber.log.Timber

/**
 * Holder of the tier list image.
 *
 * Allows to start a drag.
 *
 * @property imageView View of the image.
 * @param dragListener Listener of tier list drag events.
 * @constructor Creates a new holder instance.
 */
class TierListImageHolder(
    private val imageView: ImageView,
    dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(imageView), View.OnTouchListener {

    init {
        initListeners(dragListener)
    }

    /**
     * Binds image to the item view.
     *
     * Loads image and updates its size.
     *
     * @param image Image model.
     * @param imageSize Size of the image.
     * @param tag Drag data of the image.
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


    /**
     * If event action is [MotionEvent.ACTION_DOWN], attempts to start a drag.
     *
     * Called when a touch event is dispatched to a view.
     *
     * @param view The view the touch event has been dispatched to.
     * @param event The motion event object containing full information about the event.
     * @return True if touch event was consumed, false otherwise.
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (event?.action != MotionEvent.ACTION_DOWN) return false
        return try {
            startDrag(view)
            true
        } catch (e: Exception) {
            Timber.e(e, "Unable to start drag")
            false
        }
    }

    /**
     * Attempts to start a drag.
     *
     * This functions validates the view tag.
     *
     * @param view View that should be dragged.
     * @throws TierListImageException When view is null or has incompatible tag.
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
     * Attempts to start a drag.
     *
     * If drag started successfully, logs the information about the drag data.
     *
     * @param view View that should be dragged.
     * @param data Payload of the dragged item.
     * @throws TierListImageException If unable to start drag.
     */
    private fun startDrag(view: View, data: ImageDragData) {
        val result = startDragCompat(view, data)
        val resultDesc = "data = $data"

        if (result) {
            Timber.i("onTouch: successfully started drag ($resultDesc)")
        } else {
            throw TierListImageException("onTouch: unable to start drag ($resultDesc)")
        }
    }

    /**
     * A helper function to start drag that supports all versions.
     *
     * Starting from [Build.VERSION_CODES.N] the shadow will be fully opaque.
     *
     * @param view View that has [View.OnDragListener] set.
     * @param data Data of the image that will be dragged.
     * @return Whether the drag was started successfully.
     */
    private fun startDragCompat(view: View, data: ImageDragData): Boolean {
        val shadowBuilder = View.DragShadowBuilder(view)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data.toClipData(), shadowBuilder, data, View.DRAG_FLAG_OPAQUE)
        } else {
            @Suppress("DEPRECATION")
            view.startDrag(data.toClipData(), shadowBuilder, data, 0)
        }
    }

    /**
     * Initializes image view listeners.
     *
     * 1. Touch events are handled by [onTouch] function.
     * 2. Drag events are propagated to [dragListener].
     *
     * @param dragListener Listener of tier list drag events.
     */
    private fun initListeners(dragListener: View.OnDragListener) {
        itemView.setOnTouchListener(this)
        itemView.setOnDragListener(dragListener)
    }

    /**
     * Thrown when tier list image can't be dragged successfully.
     *
     * @param message Error message.
     * @constructor Creates a new exception instance.
     */
    private class TierListImageException(message: String) : Exception(message)
}