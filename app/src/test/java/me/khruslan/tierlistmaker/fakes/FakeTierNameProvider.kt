package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierNameProvider

class FakeTierNameProvider : TierNameProvider {
    var fakeNames: Map<Int, String> = emptyMap()
    override fun getNameByIndex(tierIndex: Int) = checkNotNull(fakeNames[tierIndex])
}