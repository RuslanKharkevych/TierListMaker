package me.khruslan.tierlistmaker.repository.tierlist.tier

import android.graphics.Color

private const val DEFAULT_SATURATION = 1f
private const val DEFAULT_VALUE = 1f

private const val HUE_RED = 0f
private const val HUE_ORANGE = 30f
private const val HUE_YELLOW = 60f
private const val HUE_GREEN = 120f
private const val HUE_CYAN = 180f
private const val HUE_BLUE = 240f
private const val HUE_MAGENTA = 300f

class TierColorProvider {
    private val defaultHueValues =
        listOf(HUE_RED, HUE_GREEN, HUE_YELLOW, HUE_ORANGE, HUE_CYAN, HUE_BLUE, HUE_MAGENTA)

    fun getColors(size: Int): List<Int> {
        val hueValues = mutableListOf<Float>()
        for (index in 0 until size) {
            hueValues += defaultHueValues.getOrElse(index) {
                calculateNewHue(hueValues)
            }
        }

        return hueValues.sorted().map { colorFromHue(it) }
    }

    private fun calculateNewHue(hueValues: List<Float>): Float {
        var maxGap = 0f
        var hue = 0f

        val iterator = hueValues.sorted().iterator()
        var startHue = iterator.next()

        while (iterator.hasNext()) {
            val endHue = iterator.next()
            val gap = endHue - startHue

            if (gap > maxGap) {
                maxGap = gap
                hue = startHue + (gap / 2)
            }

            startHue = endHue
        }

        return hue
    }

    private fun colorFromHue(hue: Float) =
        Color.HSVToColor(floatArrayOf(hue, DEFAULT_SATURATION, DEFAULT_VALUE))
}
