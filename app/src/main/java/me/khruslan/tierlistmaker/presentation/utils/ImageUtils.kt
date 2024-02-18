package me.khruslan.tierlistmaker.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.khruslan.tierlistmaker.R

/**
 * Enables crossfade animation when image is loaded into the view.
 *
 * This transition can be reused between multiple request builders for better performance.
 */
private val CrossfadeTransition = DrawableTransitionOptions().crossFade()

/**
 * Loads the image from the device file system into the [ImageView] in the tier list.
 *
 * The image is loaded with [CrossfadeTransition] and cropped to fit the view size. No placeholder
 * is shown during loading, only in case loading has failed. No data is saved to a disk cache, only
 * memory cache is used.
 *
 * @param filePath Full path to the file in device storage or assets.
 * @receiver Image view to load into.
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