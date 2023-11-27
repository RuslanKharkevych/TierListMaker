package me.khruslan.tierlistmaker.data.providers.tierlist

import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import java.util.UUID
import javax.inject.Inject

/**
 * [TierListCreator] implementation.
 *
 * @property dispatcherProvider provider of coroutine dispatchers for running suspend functions.
 * @property preferencesHelper helper that simplifies working with shared preferences.
 */
class TierListCreatorImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val preferencesHelper: PreferencesHelper
) : TierListCreator {

    override suspend fun newTierList(title: String): TierList {
        return TierList(
            id = UUID.randomUUID().toString(),
            title = title,
            zoomValue = getZoomValue(),
            tiers = MutableList(getTiersCount()) { Tier() },
            backlogImages = mutableListOf()
        )
    }

    /**
     * Asynchronously fetches scale from user preferences and converts it to the zoom value of the
     * tier list.
     *
     * @return zoom value preferred by user.
     */
    private suspend fun getZoomValue(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.scale + 1
        }
    }

    /**
     * Asynchronously fetches number of tiers from user preferences.
     *
     * @return number of tiers preferred by user.
     */
    private suspend fun getTiersCount(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.tiersCount
        }
    }
}