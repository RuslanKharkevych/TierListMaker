package me.khruslan.tierlistmaker.tests

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.FlakyTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.isEmpty
import me.khruslan.tierlistmaker.utils.isPositiveAlertButton
import me.khruslan.tierlistmaker.utils.isPreferencesRecyclerView
import me.khruslan.tierlistmaker.utils.isSeekbarPreferenceWithTitle
import me.khruslan.tierlistmaker.utils.isTierHeaderAtPosition
import me.khruslan.tierlistmaker.utils.scaledWith
import me.khruslan.tierlistmaker.utils.scrollToPreferenceWithTitle
import me.khruslan.tierlistmaker.utils.setProgress
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SettingsTests {

    private companion object {
        private const val TIER_LIST_HIP_HOP_ARTISTS = "Hip-hop artists"
        private const val TIER_LIST_VIDEO_GAMES = "Video games"
        private const val UPDATED_SCALE = 6
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @Test
    @FlakyTest
    fun changeInitialNumberOfTiers() {
        updateSeekbarPreference(R.string.pref_tiers_count_title, 0)
        launchNewTierList(TIER_LIST_HIP_HOP_ARTISTS)
        onView(withId(R.id.list_tiers)).check(matches(isEmpty()))
    }

    @Test
    fun changeDefaultScale() {
        updateSeekbarPreference(R.string.pref_scale_title, UPDATED_SCALE)
        launchNewTierList(TIER_LIST_VIDEO_GAMES)
        onView(isTierHeaderAtPosition(0)).check(matches(scaledWith(UPDATED_SCALE)))
    }

    private fun updateSeekbarPreference(@StringRes titleResId: Int, value: Int) {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.fragment_settings))
        onView(isPreferencesRecyclerView()).perform(scrollToPreferenceWithTitle(titleResId))
        onView(isSeekbarPreferenceWithTitle(titleResId)).perform(setProgress(value))
    }

    private fun launchNewTierList(title: String) {
        pressBack()
        onView(withId(R.id.btn_add_new_list)).perform(click())
        onView(withId(R.id.edit_text)).perform(typeText(title))
        onView(isPositiveAlertButton()).perform(click())
    }
}