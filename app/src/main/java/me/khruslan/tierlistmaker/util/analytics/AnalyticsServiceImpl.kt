package me.khruslan.tierlistmaker.util.analytics

import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.Firebase
import javax.inject.Inject

/**
 * [AnalyticsService] implementation.
 *
 * Logs analytics events to Firebase.
 *
 * @constructor Creates a new analytics service.
 */
class AnalyticsServiceImpl @Inject constructor(): AnalyticsService {

    /**
     * Logs analytics event.
     *
     * @param event Event to log.
     */
    override fun logEvent(event: Event) {
        Firebase.analytics.logEvent(event.name) {
            event.params?.forEach { entry ->
                param(entry.key, entry.value.toString())
            }
        }
    }
}