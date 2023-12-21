package me.khruslan.tierlistmaker.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.khruslan.tierlistmaker.R

/**
 * Enables crossfade animation when image is loaded into the view.
 */
private val CrossfadeTransition = DrawableTransitionOptions().crossFade()

/**
 * Loads the image from the device file system into the [ImageView] in the tier list.
 *
 * @param filePath full path to the file.
 * @receiver Any [ImageView].
 */
fun ImageView.loadTierListImage(filePath: String) {
    Glide.with(this)
        .load(filePath)
        .transition(CrossfadeTransition)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_broken_image)
        .into(this)
}