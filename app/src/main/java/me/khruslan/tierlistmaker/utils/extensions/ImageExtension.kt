package me.khruslan.tierlistmaker.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import me.khruslan.tierlistmaker.R

fun ImageView.loadTierListImage(filePath: String) {
    Glide.with(this)
        .load(filePath)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .into(this)
}