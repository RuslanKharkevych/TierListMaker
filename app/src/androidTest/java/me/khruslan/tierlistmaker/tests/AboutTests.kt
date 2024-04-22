package me.khruslan.tierlistmaker.tests

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.hasPreferenceSummary
import me.khruslan.tierlistmaker.utils.isPreferencesRecyclerView
import me.khruslan.tierlistmaker.utils.scrollToPreferenceWithTitle
import me.khruslan.tierlistmaker.utils.withPreferenceTitle
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AboutTests {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @Test
    fun viewAppInfo() {
        launchAboutUsPreferenceWithTitle(R.string.pref_app_info_title)
        onView(withPreferenceTitle(R.string.pref_app_info_title))
            .check(matches(hasPreferenceSummary(R.string.pref_app_info_summary)))
    }

    @Test
    fun viewAppVersion() {
        launchAboutUsPreferenceWithTitle(R.string.pref_app_version_title)
        onView(withPreferenceTitle(R.string.pref_app_version_title))
            .check(matches(hasPreferenceSummary(BuildConfig.VERSION_NAME)))
    }

    private fun launchAboutUsPreferenceWithTitle(@StringRes titleResId: Int) {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.fragment_about))
        onView(isPreferencesRecyclerView()).perform(scrollToPreferenceWithTitle(titleResId))
    }
}