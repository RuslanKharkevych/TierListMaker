package me.khruslan.tierlistmaker.data

data class LoadingProgress(val currentItem: Int, val totalItems: Int) {
    val percent = ((currentItem.toFloat() / totalItems) * 100).toInt()
}