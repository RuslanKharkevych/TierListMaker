package me.khruslan.tierlistmaker.data.tierlist

data class TierListPreview(
    val id: String,
    val title: String,
    val images: List<Image>
) {
    companion object {
        const val IMAGES_COUNT = 3
    }
}
