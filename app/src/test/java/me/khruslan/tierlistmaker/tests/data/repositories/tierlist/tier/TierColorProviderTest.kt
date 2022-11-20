package me.khruslan.tierlistmaker.tests.data.repositories.tierlist.tier

import android.graphics.Color
import io.mockk.every
import io.mockk.mockkStatic
import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.TierColorProviderImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class TierColorProviderTest {

    companion object {
        private const val DEFAULT_SATURATION = 0.8f
        private const val DEFAULT_VALUE = 1f
    }

    @Test
    fun `Provides colors for tiers`() = mockkStatic(Color::class) {
        val expectedHueValues = listOf(0f, 30f, 60f, 90f, 120f, 150f, 180f, 210f, 240f, 300f)
        val expectedColors = listOf(-52429, -6684877, -205, -13395457, -26317, -13421569, -13369549, -13369447, -13369345, -52225)
        expectedHueValues.forEachIndexed { index, hue ->
            every { Color.HSVToColor(floatArrayOf(hue, DEFAULT_SATURATION, DEFAULT_VALUE)) } returns expectedColors[index]
        }
        val actualColors = TierColorProviderImpl().getColors(expectedColors.size)

        assertEquals(expectedColors, actualColors)
    }
}