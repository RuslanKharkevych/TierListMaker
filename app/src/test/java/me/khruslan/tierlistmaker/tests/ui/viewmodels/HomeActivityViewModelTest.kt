package me.khruslan.tierlistmaker.tests.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.fakes.presentation.utils.theme.FakeThemeManager
import me.khruslan.tierlistmaker.presentation.utils.hints.dashboard.DashboardHintStep
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.presentation.viewmodels.HomeActivityViewModel
import me.khruslan.tierlistmaker.utils.awaitValue
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var fakeThemeManager: FakeThemeManager
    private lateinit var viewModel: HomeActivityViewModel

    @Before
    fun init() {
        fakeThemeManager = FakeThemeManager()
        viewModel = HomeActivityViewModel(fakeThemeManager)
    }

    @Test
    fun `Should toggle theme`() = runTest {
        fakeThemeManager.nightModeEnabled = false
        viewModel.toggleTheme()
        advanceUntilIdle()

        assertTrue(fakeThemeManager.nightModeEnabled)
    }

    @Test
    fun `Should show hint`() = runTest {
        val step = DashboardHintStep.AddNewTierList
        val observer = viewModel.hintEvent.test()
        viewModel.showHint(step)

        observer.awaitValue(step)
    }
}