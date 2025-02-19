package me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier

import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierColorProvider

class FakeTierColorProvider : TierColorProvider {
    var colors = listOf<Int>()
    override fun getColors(size: Int) = colors.take(size)
}