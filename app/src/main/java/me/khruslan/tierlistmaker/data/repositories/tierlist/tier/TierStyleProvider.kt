package me.khruslan.tierlistmaker.data.repositories.tierlist.tier

import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle

/**
 * Provider of [TierStyle] for the [Tier].
 */
interface TierStyleProvider {

    /**
     * Generates list of [TierStyle] with given [size]
     * ordered from the first tier to the the last one.
     *
     * @param size number of tiers that you need to generate styles for.
     * @return Generated tier styles.
     */
    suspend fun getTierStyles(size: Int): List<TierStyle>
}