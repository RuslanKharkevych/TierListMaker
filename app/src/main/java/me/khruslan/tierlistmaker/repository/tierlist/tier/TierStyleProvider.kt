package me.khruslan.tierlistmaker.repository.tierlist.tier

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierStyle
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable

/**
 * Provider of [TierStyle] for the [Tier].
 * All functions are running in [Dispatchers.Default] context.
 *
 * @property colorProvider provider of color for [Tier].
 * @property nameProvider provider of name for [Tier].
 * @property dispatchersProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class TierStyleProvider(
    private val colorProvider: TierColorProvider,
    private val nameProvider: TierNameProvider,
    private val dispatchersProvider: DispatcherProvidable
) {

    /**
     * Generates list of [TierStyle] with given [size]
     * ordered from the first tier to the the last one.
     *
     * @param size number of tiers that you need to generate styles for.
     * @return Generated tier styles.
     */
    suspend fun getTierStyles(size: Int): List<TierStyle> {
        return withContext(dispatchersProvider.default) {
            val colors = colorProvider.getColors(size)
            List(size) { index ->
                TierStyle(
                    color = colors[index],
                    title = nameProvider.getNameByIndex(index),
                )
            }
        }
    }
}