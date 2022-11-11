package me.khruslan.tierlistmaker.utils.log

/**
 * Service that logs analytics events.
 */
interface AnalyticsService {

    /**
     * Logs analytics event.
     *
     * @param name the name of the event.
     * @param params optional params that supply information that contextualize the event.
     */
    fun logEvent(name: String, params: Map<String, String>? = null)
}