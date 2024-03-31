package me.khruslan.tierlistmaker.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.hasBackgroundColor
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeTests {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @Test
    fun toggleTheme() {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.btn_theme)).perform(click())
        onView(isRoot()).check(matches(hasBackgroundColor(R.color.grey800_dark)))
        onView(withId(R.id.btn_theme)).perform(click())
        onView(isRoot()).check(matches(hasBackgroundColor(R.color.grey50)))
    }
}