package me.khruslan.tierlistmaker.tests

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.clickOnPreferenceWithTitle
import me.khruslan.tierlistmaker.utils.isPreferencesRecyclerView
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HintsTests {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @Test
    fun viewCollectionHints() {
        launchHintPreferenceWithTitle(R.string.pref_reorder_tier_lists_hint_title)
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_reorder_tier_lists)))
        onView(withId(R.id.btn_next)).perform(click())
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_add_new_tier_list)))
        onView(withId(R.id.btn_next)).perform(click())
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_remove_tier_list)))
        onView(withId(R.id.btn_close)).perform(click())
        onView(withId(R.id.text_hint)).check(doesNotExist())
    }

    @Test
    fun viewTierListHints() {
        launchHintPreferenceWithTitle(R.string.pref_remove_tier_hint_title)
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_remove_tier)))
        onView(withId(R.id.btn_previous)).perform(click())
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_remove_image)))
        onView(withId(R.id.btn_previous)).perform(click())
        onView(withId(R.id.text_hint)).check(matches(withText(R.string.hint_reorder_tiers)))
        onView(withId(R.id.btn_close)).perform(click())
        onView(withId(R.id.text_hint)).check(doesNotExist())
    }

    private fun launchHintPreferenceWithTitle(@StringRes titleResId: Int) {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.fragment_hints))
        onView(isPreferencesRecyclerView()).perform(clickOnPreferenceWithTitle(titleResId))
    }
}