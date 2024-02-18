package me.khruslan.tierlistmaker.data.providers.tierlist.tier

/**
 * Provider of names for tiers in a tier list.
 *
 * Synchronously provides one name at a time.
 */
interface TierNameProvider {

    /**
     * Generates tier name based on its position.
     *
     * @param tierIndex Position of the tier in a tier list.
     * @return Name of the tier.
     */
    fun getNameByIndex(tierIndex: Int): String
}