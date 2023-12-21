package me.khruslan.tierlistmaker.fakes.utils.performance

import me.khruslan.tierlistmaker.util.performace.PerformanceTrace

class NoOpPerformanceTrace : PerformanceTrace {
    override fun start() {}
    override fun putAttribute(name: String, value: Any) {}
    override fun putMetric(name: String, value: Int) {}
    override fun putMetric(name: String, value: Long) {}
    override fun stop() {}
}