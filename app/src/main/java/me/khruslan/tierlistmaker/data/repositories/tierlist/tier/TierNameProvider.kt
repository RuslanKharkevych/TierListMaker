package me.khruslan.tierlistmaker.data.repositories.tierlist.tier

import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of name for [Tier] in the [TierList].
 */
interface TierNameProvider {

    /**
     * Generates [Tier] name based on its position.
     *
     * @param tierIndex position of the [Tier].
     * @return Name of the [Tier].
     */
    fun getNameByIndex(tierIndex: Int): String
}