package me.khruslan.tierlistmaker.tests.ui.models

import me.khruslan.tierlistmaker.ui.models.LoadingProgress
import org.junit.Assert.assertEquals
import org.junit.Test

class LoadingProgressTest {

    @Test
    fun `Calculates determinate loading percent`() {
        val loadingProgress = LoadingProgress.Determinate(currentItem = 4, totalItems = 7)
        val expectedPercent = 57

        assertEquals(expectedPercent, loadingProgress.percent)
    }
}