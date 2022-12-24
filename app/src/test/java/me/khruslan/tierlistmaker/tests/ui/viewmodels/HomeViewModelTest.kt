package me.khruslan.tierlistmaker.tests.ui.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.fakes.utils.theme.FakeThemeManager
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.ui.viewmodels.HomeViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var fakeThemeManager: FakeThemeManager
    private lateinit var viewModel: HomeViewModel

    @Before
    fun init() {
        fakeThemeManager = FakeThemeManager()
        viewModel = HomeViewModel(fakeThemeManager)
    }

    @Test
    fun `Should toggle theme`() = runTest {
        fakeThemeManager.nightModeEnabled = false
        viewModel.toggleTheme()
        advanceUntilIdle()

        assertTrue(fakeThemeManager.nightModeEnabled)
    }
}