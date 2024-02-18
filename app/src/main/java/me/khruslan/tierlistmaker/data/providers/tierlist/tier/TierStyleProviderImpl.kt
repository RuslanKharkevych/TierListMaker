package me.khruslan.tierlistmaker.data.providers.tierlist.tier

import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import javax.inject.Inject

/**
 * [TierStyleProvider] implementation.
 *
 * Moves tier styles generation to the background thread.
 *
 * @property colorProvider Provides tier colors.
 * @property nameProvider Provides tier names.
 * @property dispatchersProvider Provides default dispatcher.
 * @constructor Creates a new tier style provider instance.
 */
class TierStyleProviderImpl @Inject constructor(
    private val colorProvider: TierColorProvider,
    private val nameProvider: TierNameProvider,
    private val dispatchersProvider: DispatcherProvider
) : TierStyleProvider {

    /**
     * Generates list of tier styles with given size.
     *
     * Ordered from the first tier to the the last one.
     *
     * @param size Number of tiers to generate styles for.
     * @return Generated tier styles.
     */
    override suspend fun getTierStyles(size: Int): List<TierStyle> {
        return withContext(dispatchersProvider.default) {
            val colors = colorProvider.getColors(size)
            List(size) { index ->
                TierStyle(
                    color = colors[index],
                    title = nameProvider.getNameByIndex(index)
                )
            }
        }
    }
}