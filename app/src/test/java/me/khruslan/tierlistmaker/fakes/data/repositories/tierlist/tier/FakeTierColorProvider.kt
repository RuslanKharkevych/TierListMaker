package me.khruslan.tierlistmaker.fakes.data.repositories.tierlist.tier

import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierColorProvider

class FakeTierColorProvider : TierColorProvider {
    var colors = listOf<Int>()
    override fun getColors(size: Int) = colors.take(size)
}