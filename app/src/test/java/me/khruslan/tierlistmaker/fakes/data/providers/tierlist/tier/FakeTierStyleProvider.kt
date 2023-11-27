package me.khruslan.tierlistmaker.fakes.data.providers.tierlist.tier

import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierStyleProvider

class FakeTierStyleProvider : TierStyleProvider {
    var styles = listOf<TierStyle>()
    override suspend fun getTierStyles(size: Int) = styles.take(size)
}