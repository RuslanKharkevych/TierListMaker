package me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier

import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierNameProvider

class FakeTierNameProvider : TierNameProvider {
    var names = listOf<String>()
    override fun getNameByIndex(tierIndex: Int) = names[tierIndex]
}