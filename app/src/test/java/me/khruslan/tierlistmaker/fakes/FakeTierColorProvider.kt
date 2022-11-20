package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierColorProvider

class FakeTierColorProvider : TierColorProvider {
    var fakeColors: List<Int> = emptyList()
    override fun getColors(size: Int) = fakeColors
}