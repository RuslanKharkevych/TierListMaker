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
     * @param name The name of the event.
     * @param params Optional params that supply information that contextualize the event.
     */
    override fun logEvent(name: String, params: Map<String, String>?) {
        Firebase.analytics.logEvent(name) {
            params?.forEach { entry ->
                param(entry.key, entry.value)
            }
        }
    }
}