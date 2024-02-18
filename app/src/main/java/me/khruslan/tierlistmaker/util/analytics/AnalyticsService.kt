package me.khruslan.tierlistmaker.util.analytics

/**
 * Service that logs analytics events.
 *
 * Can be replaced with no-op implementation in tests.
 */
interface AnalyticsService {

    /**
     * Logs analytics event.
     *
     * @param name The name of the event.
     * @param params Optional params that supply information that contextualize the event.
     */
    fun logEvent(name: String, params: Map<String, String>? = null)
}