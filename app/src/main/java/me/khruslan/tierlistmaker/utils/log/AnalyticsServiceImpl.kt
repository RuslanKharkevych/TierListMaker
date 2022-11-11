package me.khruslan.tierlistmaker.utils.log

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

/**
 * [AnalyticsService] implementation that logs analytics event to Firebase.
 */
class AnalyticsServiceImpl : AnalyticsService {

    override fun logEvent(name: String, params: Map<String, String>?) {
        Firebase.analytics.logEvent(name) {
            params?.forEach { entry ->
                param(entry.key, entry.value)
            }
        }
    }
}