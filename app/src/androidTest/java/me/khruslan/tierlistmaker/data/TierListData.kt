package me.khruslan.tierlistmaker.data

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

class TierListData(private val tierListFactory: () -> TierList) {

    private val tierList by lazy { tierListFactory() }

    val lastTierPosition get() = tierList.tiers.lastIndex
    val newTierPosition get() = lastTierPosition + 1
    val increasedZoomValue get() = tierList.zoomValue + 1
    val decreasedZoomValue get() = tierList.zoomValue - 1
}