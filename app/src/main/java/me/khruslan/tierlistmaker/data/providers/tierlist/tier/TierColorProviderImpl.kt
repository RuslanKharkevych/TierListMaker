package me.khruslan.tierlistmaker.data.providers.tierlist.tier

import android.graphics.Color
import javax.inject.Inject

/**
 * [TierColorProvider] implementation.
 *
 * Uses HSV color model for calculations.
 *
 * @constructor Creates a new tier color provider instance.
 */
class TierColorProviderImpl @Inject constructor(): TierColorProvider {

    /**
     * Constant HSV attributes for internal usage.
     */
    private companion object HSVDefaults {

        /**
         * Saturation of all colors.
         */
        private const val DEFAULT_SATURATION = 0.8f

        /**
         * Value (brightness) of all colors.
         */
        private const val DEFAULT_VALUE = 1f

        /**
         * Hue of the red color.
         */
        private const val HUE_RED = 0f

        /**
         * Hue of the orange color.
         */
        private const val HUE_ORANGE = 30f

        /**
         * Hue of the yellow color.
         */
        private const val HUE_YELLOW = 60f

        /**
         * Hue of the green color.
         */
        private const val HUE_GREEN = 120f

        /**
         * Hue of the cyan color.
         */
        private const val HUE_CYAN = 180f

        /**
         * Hue of the blue color.
         */
        private const val HUE_BLUE = 240f

        /**
         * Hue of the magenta color.
         */
        private const val HUE_MAGENTA = 300f
    }

    /**
     * Pre-defined hue values.
     *
     * Represents the color scheme of tiers.
     */
    private val defaultHueValues =
        listOf(HUE_RED, HUE_GREEN, HUE_YELLOW, HUE_ORANGE, HUE_CYAN, HUE_BLUE, HUE_MAGENTA)

    /**
     * Generates the list of colors for the tiers.
     *
     * Firstly creates colors from the default hue values. If those are not enough, calculates new
     * hue value and creates a color from it. Resulting colors are sorted by hue.
     *
     * @param size Number of colors to generate.
     * @return List of generated colors.
     */
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
     *
     * The algorithm seeks for the biggest gap between hue values. If there are several equal gaps,
     * the first one will be chosen. After that the value from the middle of the gap is picked.
     *
     * @param hueValues List of hue values of already generated colors.
     * @return Hue value of the new color.
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
     * Creates color from its hue.
     *
     * For saturation and value [DEFAULT_SATURATION] and [DEFAULT_VALUE] values are used.
     *
     * @param hue Hue of the HSV color.
     * @return Created color as [Int].
     */
    private fun colorFromHue(hue: Float) =
        Color.HSVToColor(floatArrayOf(hue, DEFAULT_SATURATION, DEFAULT_VALUE))
}
