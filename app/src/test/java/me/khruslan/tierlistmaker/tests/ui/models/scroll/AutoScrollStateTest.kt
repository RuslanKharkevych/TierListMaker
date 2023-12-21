package me.khruslan.tierlistmaker.tests.ui.models.scroll

import me.khruslan.tierlistmaker.presentation.models.scroll.AutoScrollDirection
import me.khruslan.tierlistmaker.presentation.models.scroll.AutoScrollState
import org.junit.Assert.assertEquals
import org.junit.Test

class AutoScrollStateTest {

    @Test
    fun `Creates scrolling up state`() {
        val expectedState = AutoScrollState.Scrolling(AutoScrollDirection.Up)
        val actualState = AutoScrollState.scrollingUp()

        assertEquals(expectedState, actualState)
    }

    @Test
    fun `Creates scrolling down state`() {
        val expectedState = AutoScrollState.Scrolling(AutoScrollDirection.Down)
        val actualState = AutoScrollState.scrollingDown()

        assertEquals(expectedState, actualState)
    }
}