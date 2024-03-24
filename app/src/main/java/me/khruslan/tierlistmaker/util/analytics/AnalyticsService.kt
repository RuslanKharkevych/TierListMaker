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
     * @param event Event to log.
     */
    fun logEvent(event: Event)
}