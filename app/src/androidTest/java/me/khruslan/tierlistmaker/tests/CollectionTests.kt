package me.khruslan.tierlistmaker.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.clickOnTierListWithTitle
import me.khruslan.tierlistmaker.utils.dragToTopOf
import me.khruslan.tierlistmaker.utils.fetchTierListTitleAtPosition
import me.khruslan.tierlistmaker.utils.hasToolbarTitle
import me.khruslan.tierlistmaker.utils.isEmpty
import me.khruslan.tierlistmaker.utils.isPositiveAlertButton
import me.khruslan.tierlistmaker.utils.isTierListAtPosition
import me.khruslan.tierlistmaker.utils.swipeTierListWithTitle
import me.khruslan.tierlistmaker.utils.waitUntil
import me.khruslan.tierlistmaker.utils.withTierListTitle
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CollectionTests {

    private companion object {
        private const val NEW_TIER_LIST_TITLE = "Football players"
        private const val SRC_TIER_LIST_POS = 1
        private const val TGT_TIER_LIST_POS = 0
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @Test
    fun addTierList() {
        onView(withId(R.id.btn_add_new_list)).perform(click())
        onView(withId(R.id.edit_text)).perform(typeText(NEW_TIER_LIST_TITLE))
        onView(isPositiveAlertButton()).perform(click())
        onView(withId(R.id.toolbar)).check(matches(hasToolbarTitle(NEW_TIER_LIST_TITLE)))
        pressBack()
        onView(withTierListTitle(NEW_TIER_LIST_TITLE)).check(matches(isDisplayed()))
    }

    @Test
    fun openTierList() {
        onView(withId(R.id.list_tier_lists)).perform(
            waitUntil(not(isEmpty())),
            clickOnTierListWithTitle(R.string.tier_list_vehicles)
        )
        onView(withId(R.id.toolbar)).check(matches(hasToolbarTitle(R.string.tier_list_vehicles)))
    }

    @Test
    fun reorderTierLists() {
        onView(withId(R.id.list_tier_lists)).perform(waitUntil(not(isEmpty())))
        val sourceTierListTitle = onView(withId(R.id.list_tier_lists))
            .fetchTierListTitleAtPosition(SRC_TIER_LIST_POS)
        onView(isTierListAtPosition(SRC_TIER_LIST_POS))
            .dragToTopOf(isTierListAtPosition(TGT_TIER_LIST_POS))
        onView(withTierListTitle(sourceTierListTitle))
            .check(matches(isTierListAtPosition(TGT_TIER_LIST_POS)))
    }

    @Test
    fun deleteTierList() {
        onView(withId(R.id.list_tier_lists)).perform(
            waitUntil(not(isEmpty())),
            swipeTierListWithTitle(R.string.tier_list_animals)
        )
        onView(isPositiveAlertButton()).perform(click())
        onView(withTierListTitle(R.string.tier_list_animals)).check(doesNotExist())
    }
}