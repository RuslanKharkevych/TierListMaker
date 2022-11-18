package me.khruslan.tierlistmaker.repository.tierlist.tier

import android.graphics.Color
import me.khruslan.tierlistmaker.data.tierlist.Tier

private const val DEFAULT_SATURATION = 0.8f
private const val DEFAULT_VALUE = 1f // Brightness

private const val HUE_RED = 0f
private const val HUE_ORANGE = 30f
private const val HUE_YELLOW = 60f
private const val HUE_GREEN = 120f
private const val HUE_CYAN = 180f
private const val HUE_BLUE = 240f
private const val HUE_MAGENTA = 300f

/**
 * [TierColorProvider] implementation.
 */
class TierColorProviderImpl : TierColorProvider {

    /**
     * Pre-defined [Tier] colors.
     */
    private val defaultHueValues =
        listOf(HUE_RED, HUE_GREEN, HUE_YELLOW, HUE_ORANGE, HUE_CYAN, HUE_BLUE, HUE_MAGENTA)

    override fun getColors(size: Int): List<Int> {
        val hueValues = mutableListOf<Float>()
        for (index in 0 until size) {
            hueValues += defaultHueValues.getOrElse(index) {
                calculateNewHue(hueValues)
            }
        }

        return hueValues.sorted().map { colorFromHue(it) }
    }

    /**
     * Calculates the hue value for the new color based on the list of hue values.
     * The algorithm seeks for the biggest gap between hue values.
     * If there are several equal gaps, the first one will be chosen.
     * After that the value from the middle of the gap is picked.
     *
     * @param hueValues list of hue values of already generated colors.
     * @return hue value of the new color.
     */
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

    /**
     * Creates color from HSV. Only hue value is provided to this function.
     * For saturation and value [DEFAULT_SATURATION] and [DEFAULT_VALUE] values are used.
     *
     * @param hue hue of the HSV color.
     * @return created color as [Int].
     */
    private fun colorFromHue(hue: Float) =
        Color.HSVToColor(floatArrayOf(hue, DEFAULT_SATURATION, DEFAULT_VALUE))
}
