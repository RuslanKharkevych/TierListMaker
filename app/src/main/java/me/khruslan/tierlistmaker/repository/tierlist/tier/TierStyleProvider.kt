package me.khruslan.tierlistmaker.repository.tierlist.tier

import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.TierStyle
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable

class TierStyleProvider(
    private val colorProvider: TierColorProvider,
    private val nameProvider: TierNameProvider,
    private val dispatchersProvider: DispatcherProvidable
) {
    suspend fun getTierStyles(size: Int) = withContext(dispatchersProvider.default) {
        val colors = colorProvider.getColors(size)
        List(size) { index ->
            TierStyle(
                color = colors[index],
                title = nameProvider.getNameByIndex(index),
            )
        }
    }
}