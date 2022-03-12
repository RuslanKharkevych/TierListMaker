package me.khruslan.tierlistmaker.ui.holders

import android.view.View
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.utils.extensions.loadTierListImage
import me.khruslan.tierlistmaker.utils.extensions.startDragCompat
import timber.log.Timber

class TierListImageHolder(
    private val imageView: ImageView,
    dragListener: View.OnDragListener
) : RecyclerView.ViewHolder(imageView), View.OnLongClickListener {

    init {
        itemView.setOnLongClickListener(this)
        itemView.setOnDragListener(dragListener)
    }

    fun bind(imageUrl: String?, imageSize: Int, tag: ImageDragData) {
        imageView.tag = tag
        imageView.updateLayoutParams {
            height = imageSize
            width = imageSize
        }

        if (imageUrl != null) {
            imageView.loadTierListImage(imageUrl)
        } else {
            imageView.setImageResource(R.drawable.ic_crop_free)
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