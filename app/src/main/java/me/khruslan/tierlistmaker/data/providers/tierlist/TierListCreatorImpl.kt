package me.khruslan.tierlistmaker.data.providers.tierlist

import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import java.util.UUID
import javax.inject.Inject

/**
 * [TierListCreator] implementation.
 *
 * Moves tier list creation to the background thread.
 *
 * @property dispatcherProvider Provides IO dispatcher.
 * @property preferencesHelper Fetches zoom value and tiers count.
 * @constructor Creates a new tier list creator instance.
 */
class TierListCreatorImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val preferencesHelper: PreferencesHelper
) : TierListCreator {

    /**
     * Creates a new empty tier list.
     *
     * Zoom value and tiers count are fetched from user preferences.
     *
     * @param title Title of the tier list.
     * @return Created tier list.
     */
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
     * @return Zoom value preferred by user.
     */
    private suspend fun getZoomValue(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.scale + 1
        }
    }

    /**
     * Asynchronously fetches number of tiers from user preferences.
     *
     * @return Number of tiers preferred by user.
     */
    private suspend fun getTiersCount(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.tiersCount
        }
    }
}