package me.khruslan.tierlistmaker.tests

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.doesNotHaveExtraWithKey
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.FeedbackFragment.Constants.APP_DETAILS_PLAY_MARKET_URL
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.presentation.utils.FeedbackUtils.MAILTO_URI_SCHEME
import me.khruslan.tierlistmaker.presentation.utils.FeedbackUtils.RECIPIENT_EMAIL
import me.khruslan.tierlistmaker.utils.clickOnPreferenceWithTitle
import me.khruslan.tierlistmaker.utils.isPreferencesRecyclerView
import me.khruslan.tierlistmaker.utils.targetContext
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FeedbackTests {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @get:Rule(order = 2)
    val intentsRule = IntentsRule()

    @Before
    fun stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(ActivityResult(RESULT_OK, null))
    }

    @Test
    fun sendFeedback() {
        launchFeedbackPreferenceWithTitle(R.string.pref_contact_us_title)
        intended(
            allOf(
                hasAction(ACTION_SENDTO),
                hasData(MAILTO_URI_SCHEME),
                hasExtra(EXTRA_EMAIL, arrayOf(RECIPIENT_EMAIL)),
                hasExtra(
                    EXTRA_SUBJECT,
                    targetContext.getString(R.string.send_feedback_email_subject)
                ),
                doesNotHaveExtraWithKey(EXTRA_TEXT)
            )
        )
    }

    @Test
    fun reportBug() {
        launchFeedbackPreferenceWithTitle(R.string.pref_report_bug_title)
        intended(
            allOf(
                hasAction(ACTION_SENDTO),
                hasData(MAILTO_URI_SCHEME),
                hasExtra(EXTRA_EMAIL, arrayOf(RECIPIENT_EMAIL)),
                hasExtra(EXTRA_SUBJECT, targetContext.getString(R.string.bug_report_email_subject)),
                hasExtraWithKey(EXTRA_TEXT)
            )
        )
    }

    @Test
    fun rateApp() {
        launchFeedbackPreferenceWithTitle(R.string.pref_rate_app_title)
        intended(allOf(hasAction(ACTION_VIEW), hasData(APP_DETAILS_PLAY_MARKET_URL)))
    }

    private fun launchFeedbackPreferenceWithTitle(@StringRes titleResId: Int) {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.fragment_feedback))
        onView(isPreferencesRecyclerView()).perform(clickOnPreferenceWithTitle(titleResId))
    }
}