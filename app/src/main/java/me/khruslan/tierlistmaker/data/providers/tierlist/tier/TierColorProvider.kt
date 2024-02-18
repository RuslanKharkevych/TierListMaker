package me.khruslan.tierlistmaker.data.providers.tierlist.tier

/**
 * Provider of colors for tiers in the tier list.
 *
 * The colors are synchronously provided for all tiers at once.
 */
interface TierColorProvider {

    /**
     * Generates the list of colors for the tiers.
     *
     * Ordered from the first tier to the last.
     *
     * @param size Number of colors to generate.
     * @return List of generated colors.
     */
    fun getColors(size: Int): List<Int>
}