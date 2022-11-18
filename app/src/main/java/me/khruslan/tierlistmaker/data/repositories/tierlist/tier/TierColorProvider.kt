package me.khruslan.tierlistmaker.data.repositories.tierlist.tier

import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of color for [Tier] in the [TierList].
 */
interface TierColorProvider {

    /**
     * Generates the list of colors for the tiers. Ordered from first tier to last.
     *
     * @param size number of colors in the list.
     * @return list of generated colors.
     */
    fun getColors(size: Int): List<Int>
}