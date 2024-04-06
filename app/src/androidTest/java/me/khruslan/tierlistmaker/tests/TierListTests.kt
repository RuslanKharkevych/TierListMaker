package me.khruslan.tierlistmaker.tests

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_STREAM
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.doubleClick
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.intent.matcher.IntentMatchers.hasFlag
import androidx.test.espresso.intent.matcher.IntentMatchers.hasType
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.FlakyTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.TierListDataProvider
import me.khruslan.tierlistmaker.data.providers.file.FileManager.Constants.MIME_TYPE_IMAGE_JPEG
import me.khruslan.tierlistmaker.presentation.screens.home.HomeActivity
import me.khruslan.tierlistmaker.utils.clickOnTierListWithTitle
import me.khruslan.tierlistmaker.utils.dragTo
import me.khruslan.tierlistmaker.utils.dragToBottomOf
import me.khruslan.tierlistmaker.utils.fetchTierListImage
import me.khruslan.tierlistmaker.utils.fetchTierListImages
import me.khruslan.tierlistmaker.utils.hasEmptyToolbarSubtitle
import me.khruslan.tierlistmaker.utils.hasToolbarSubtitle
import me.khruslan.tierlistmaker.utils.hasToolbarTitle
import me.khruslan.tierlistmaker.utils.isEmpty
import me.khruslan.tierlistmaker.utils.isFirstTierListImage
import me.khruslan.tierlistmaker.utils.isPositiveAlertButton
import me.khruslan.tierlistmaker.utils.isSnackbarAction
import me.khruslan.tierlistmaker.utils.isTierAtPosition
import me.khruslan.tierlistmaker.utils.isTierHeaderAtPosition
import me.khruslan.tierlistmaker.utils.isTierListImageAtPosition
import me.khruslan.tierlistmaker.utils.scaledWithZoomValue
import me.khruslan.tierlistmaker.utils.scrollToLastItem
import me.khruslan.tierlistmaker.utils.waitUntil
import me.khruslan.tierlistmaker.utils.withTierListImage
import me.khruslan.tierlistmaker.utils.withTierListImages
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.any
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TierListTests {

    private companion object {
        private const val RENAMED_TIER_LIST_TITLE = "Fast food restaurants"
        private const val SRC_IMG_POS = 3
        private const val SRC_IMG_TIER_POS = 2
        private const val TGT_IMG_POS = 1
        private const val TGT_IMG_TIER_POS = 0
        private const val SRC_TIER_POS = 0
        private const val TGT_TIER_POS = 2

        private lateinit var dataProvider: TierListDataProvider

        @JvmStatic
        @BeforeClass
        fun initDataProvider() {
            dataProvider = TierListDataProvider()
        }
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = activityScenarioRule<HomeActivity>()

    @get:Rule(order = 2)
    val intentsRule = IntentsRule()

    @Before
    fun stubAllExternalIntents() {
        val dummyIntent = Intent().apply { data = Uri.EMPTY }
        intending(not(isInternal())).respondWith(ActivityResult(RESULT_OK, dummyIntent))
    }

    @Test
    fun renameTierList() {
        openTierListWithTitle(R.string.tier_list_fast_food)
        onView(withId(R.id.item_rename_tier_list)).perform(click())
        onView(withId(R.id.edit_text)).perform(replaceText(RENAMED_TIER_LIST_TITLE))
        onView(isPositiveAlertButton()).perform(click())
        onView(withId(R.id.toolbar)).check(matches(hasToolbarTitle(RENAMED_TIER_LIST_TITLE)))
    }

    @Test
    fun addNewTier() = dataProvider.withTierListData(R.string.tier_list_zodiac_signs) {
        openTierListWithTitle(R.string.tier_list_zodiac_signs)
        onView(withId(R.id.btn_add_new_tier)).perform(click())
        onView(withId(R.id.list_tiers)).perform(scrollToLastItem())
        onView(isTierHeaderAtPosition(newTierPosition)).check(matches(isDisplayed()))
    }

    @Test
    fun addNewImage() {
        openTierListWithTitle(R.string.tier_list_mythical_creatures)
        onView(withId(R.id.btn_add_new_image)).perform(click())
        onView(withId(R.id.list_backlog_images)).check(matches(not(isEmpty())))
    }

    @Test
    fun changeScale() = dataProvider.withTierListData(R.string.tier_list_flowers) {
        openTierListWithTitle(R.string.tier_list_flowers)
        onView(withId(R.id.btn_zoom_out)).perform(click())
        onView(isFirstTierListImage()).check(matches(scaledWithZoomValue(increasedZoomValue)))
        onView(withId(R.id.btn_zoom_in)).perform(doubleClick())
        onView(isFirstTierListImage()).check(matches(scaledWithZoomValue(decreasedZoomValue)))
    }

    @Test
    @FlakyTest
    fun viewTierList() {
        openTierListWithTitle(R.string.tier_list_sports)
        onView(withId(R.id.btn_view)).perform(click()).check(matches(isNotEnabled()))
        waitUntilLoadingIsCompleted()
        intended(
            allOf(
                hasAction(ACTION_VIEW),
                hasData(any(Uri::class.java)),
                hasType(MIME_TYPE_IMAGE_JPEG),
                hasFlag(FLAG_GRANT_READ_URI_PERMISSION)
            )
        )
    }

    @Test
    fun shareTierList() {
        openTierListWithTitle(R.string.tier_list_fruits)
        onView(withId(R.id.btn_share)).perform(click()).check(matches(isNotEnabled()))
        waitUntilLoadingIsCompleted()
        intended(
            allOf(
                hasAction(ACTION_SEND),
                hasExtraWithKey(EXTRA_STREAM),
                hasType(MIME_TYPE_IMAGE_JPEG),
                hasFlag(FLAG_GRANT_READ_URI_PERMISSION)
            )
        )
    }

    @Test
    fun reorderTiers() {
        openTierListWithTitle(R.string.tier_list_mythical_creatures)
        val sourceImages = onView(isTierAtPosition(SRC_TIER_POS)).fetchTierListImages()
        onView(isTierHeaderAtPosition(SRC_TIER_POS))
            .dragToBottomOf(isTierHeaderAtPosition(TGT_TIER_POS))
        onView(withTierListImages(sourceImages)).check(matches(isTierAtPosition(TGT_TIER_POS)))
    }

    @Test
    @Ignore("Issue with back navigation gesture on swipeRight()")
    fun removeTier() = dataProvider.withTierListData(R.string.tier_list_school_subjects) {
        openTierListWithTitle(R.string.tier_list_school_subjects)
        onView(withId(R.id.list_tiers)).perform(scrollToLastItem())
        onView(isTierHeaderAtPosition(lastTierPosition)).perform(swipeRight()).check(doesNotExist())
        onView(withId(R.id.list_backlog_images)).check(matches(not(isEmpty())))
    }

    @Test
    fun dragAndDropImage() {
        openTierListWithTitle(R.string.tier_list_fruits)
        val sourceImage = onView(isTierListImageAtPosition(SRC_IMG_TIER_POS, SRC_IMG_POS))
            .fetchTierListImage()
        onView(withTierListImage(sourceImage))
            .dragTo(isTierListImageAtPosition(TGT_IMG_TIER_POS, TGT_IMG_POS))
            .check(matches(isTierListImageAtPosition(TGT_IMG_TIER_POS, TGT_IMG_POS)))
    }

    @Test
    fun removeImage() {
        openTierListWithTitle(R.string.tier_list_musical_instruments)
        val imageToRemove = onView(isFirstTierListImage()).fetchTierListImage()
        onView(withTierListImage(imageToRemove))
            // Synchronously changes to R.id.item_remove_image during dragging
            .dragTo(withId(R.id.item_rename_tier_list))
            .check(doesNotExist())
        onView(isSnackbarAction()).perform(click())
        onView(withTierListImage(imageToRemove)).check(matches(isFirstTierListImage()))
    }

    private fun openTierListWithTitle(@StringRes title: Int) {
        onView(withId(R.id.list_tier_lists)).perform(
            waitUntil(not(isEmpty())),
            clickOnTierListWithTitle(title)
        )
    }

    private fun waitUntilLoadingIsCompleted() {
        onView(withId(R.id.toolbar))
            .check(matches(hasToolbarSubtitle(R.string.toolbar_subtitle_indeterminate_loading)))
        onView(withId(R.id.progress_loading))
            .check(matches(isDisplayed()))
            .perform(waitUntil(not(isDisplayed())))
        onView(withId(R.id.toolbar)).check(matches(hasEmptyToolbarSubtitle()))
    }
}