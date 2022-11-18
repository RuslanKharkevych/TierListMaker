package me.khruslan.tierlistmaker.fakes

import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierStyleProvider

class FakeTierStyleProvider : TierStyleProvider {
    var fakeStyles = emptyList<TierStyle>()
    var processedSize: Int? = null

    override suspend fun getTierStyles(size: Int): List<TierStyle> {
        processedSize = size
        return fakeStyles
    }
}