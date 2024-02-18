package me.khruslan.tierlistmaker.data.providers.tierlist.tier

import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle

/**
 * Provider of tier styles.
 *
 * The styles are asynchronously provided for all tiers at once. The subclasses must ensure that it
 * can be safely called from the main thread.
 */
interface TierStyleProvider {

    /**
     * Generates list of tier styles with given size.
     *
     * Ordered from the first tier to the the last one.
     *
     * @param size Number of tiers to generate styles for.
     * @return Generated tier styles.
     */
    suspend fun getTierStyles(size: Int): List<TierStyle>
}