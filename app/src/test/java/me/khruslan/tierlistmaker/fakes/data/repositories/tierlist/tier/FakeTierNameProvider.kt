package me.khruslan.tierlistmaker.fakes.data.repositories.tierlist.tier

import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierNameProvider

class FakeTierNameProvider : TierNameProvider {
    var names = listOf<String>()
    override fun getNameByIndex(tierIndex: Int) = names[tierIndex]
}