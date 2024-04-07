package me.khruslan.tierlistmaker.utils

import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.contrib.RecyclerViewActions.PositionableRecyclerViewAction
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToLastPosition
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import java.util.concurrent.TimeoutException


fun clickOnPreferenceWithTitle(@StringRes titleResId: Int): PositionableRecyclerViewAction {
    return actionOnItem<RecyclerView.ViewHolder>(
        hasDescendant(withPreferenceTitle(titleResId)),
        click()
    )
}

fun clickOnTierListWithTitle(@StringRes titleResId: Int): ViewAction {
    return actionOnItem<RecyclerView.ViewHolder>(withTierListTitle(titleResId), click())
}

fun scrollToLastItem(): ViewAction {
    return scrollToLastPosition<RecyclerView.ViewHolder>()
}

fun scrollToPreferenceWithTitle(@StringRes titleResId: Int): ViewAction {
    return scrollTo<RecyclerView.ViewHolder>(hasDescendant(withPreferenceTitle(titleResId)))
}

fun setProgress(progress: Int): ViewAction {
    return GeneralClickAction(
        Tap.SINGLE,
        SeekbarProgressCoordinatesProvider(progress),
        Press.FINGER,
        InputDevice.SOURCE_TOUCHSCREEN,
        MotionEvent.BUTTON_PRIMARY
    )
}

fun swipeRightFromCenter(): ViewAction {
    return GeneralSwipeAction(
        Swipe.FAST,
        GeneralLocation.CENTER,
        GeneralLocation.CENTER_RIGHT,
        Press.FINGER
    )
}

fun swipeTierListWithTitle(@StringRes titleResId: Int): PositionableRecyclerViewAction {
    return actionOnItem<RecyclerView.ViewHolder>(withTierListTitle(titleResId), swipeRight())
}

fun waitUntil(viewMatcher: Matcher<View>, timeoutMillis: Long = 5_000L): ViewAction {
    return WaitUntilAction(viewMatcher, timeoutMillis)
}

private class SeekbarProgressCoordinatesProvider(private val progress: Int): CoordinatesProvider {
    override fun calculateCoordinates(view: View): FloatArray {
        val seekBar = view as SeekBar
        val viewLocation = IntArray(2)
        seekBar.getLocationOnScreen(viewLocation)

        val width = seekBar.width - seekBar.paddingLeft - seekBar.paddingRight
        val relativeProgress = (progress.toFloat() / seekBar.max).coerceAtMost(1f)
        val x = viewLocation[0] + width * relativeProgress + seekBar.paddingLeft
        val y = viewLocation[1] + seekBar.height / 2f

        return floatArrayOf(x, y)
    }
}

private class WaitUntilAction(
    private val viewMatcher: Matcher<View>,
    private val timeoutMillis: Long
) : ViewAction {

    private val startTime = System.nanoTime()

    override fun getDescription(): String {
        return "wait up to $timeoutMillis ms until the codition $viewMatcher is satisfied"
    }

    override fun getConstraints(): Matcher<View> {
        return any(View::class.java)
    }

    override fun perform(uiController: UiController, view: View) {
        var conditionSatisfied = viewMatcher.matches(view)
        while (!conditionSatisfied) {
            if (System.nanoTime() - startTime > timeoutMillis * 1_000_000L) {
                val cause = TimeoutException("Condition was not satisfied after $timeoutMillis ms")
                throw PerformException.Builder()
                    .withActionDescription(description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(cause)
                    .build()
            }

            uiController.loopMainThreadForAtLeast(10L)
            conditionSatisfied = viewMatcher.matches(view)
        }
    }
}