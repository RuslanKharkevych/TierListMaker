package me.khruslan.tierlistmaker.repository.tierlist.tier

private const val S_TIER_CHAR = 'S'
private const val FIRST_TIER_CHAR = 'A'
private const val LAST_TIER_CHAR = 'Z'

/**
 * [TierNameProvider] implementation.
 */
class TierNameProviderImpl : TierNameProvider {

    override fun getNameByIndex(tierIndex: Int): String {
        return when {
            tierIndex == 0 -> S_TIER_CHAR.toString()
            tierIndex > LAST_TIER_CHAR - FIRST_TIER_CHAR + 1 ->
                throw IllegalArgumentException("Unable to get tier name for index: $tierIndex")
            tierIndex > S_TIER_CHAR.code -> (FIRST_TIER_CHAR + tierIndex).toString()
            else -> (FIRST_TIER_CHAR + tierIndex - 1).toString()
        }
    }
}
