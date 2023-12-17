package me.khruslan.tierlistmaker.fakes.utils.performance

import me.khruslan.tierlistmaker.utils.performace.PerformanceService

class NoOpPerformanceService : PerformanceService {
    override fun startTrace(name: String) = NoOpPerformanceTrace()
}