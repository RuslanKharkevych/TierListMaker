package me.khruslan.tierlistmaker.repository.tierlist.tier

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierStyle
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider

/**
 * [TierStyleProvider] implementation. All functions are running in [Dispatchers.Default] context.
 *
 * @property colorProvider provider of color for [Tier].
 * @property nameProvider provider of name for [Tier].
 * @property dispatchersProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class TierStyleProviderImpl(
    private val colorProvider: TierColorProvider,
    private val nameProvider: TierNameProvider,
    private val dispatchersProvider: DispatcherProvider
) : TierStyleProvider {

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