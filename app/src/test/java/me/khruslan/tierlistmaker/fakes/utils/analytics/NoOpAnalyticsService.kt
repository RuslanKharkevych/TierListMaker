package me.khruslan.tierlistmaker.fakes.utils.analytics

import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.Event

class NoOpAnalyticsService : AnalyticsService {
    override fun logEvent(event: Event) {}
}