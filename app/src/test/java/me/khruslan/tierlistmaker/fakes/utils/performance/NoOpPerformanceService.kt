package me.khruslan.tierlistmaker.fakes.utils.performance

import me.khruslan.tierlistmaker.util.performance.PerformanceService

class NoOpPerformanceService : PerformanceService {
    override fun startTrace(name: String) = NoOpPerformanceTrace()
}