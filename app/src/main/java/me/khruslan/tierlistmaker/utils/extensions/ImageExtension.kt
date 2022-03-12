package me.khruslan.tierlistmaker.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import me.khruslan.tierlistmaker.R

fun ImageView.loadTierListImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .into(this)
}