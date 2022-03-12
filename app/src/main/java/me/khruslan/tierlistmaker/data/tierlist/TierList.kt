package me.khruslan.tierlistmaker.data.tierlist

data class TierList(
    var zoomValue: Int,
    var tiers: MutableList<Tier>,
    val backlogImageUrls: MutableList<String>
)