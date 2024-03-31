package me.khruslan.tierlistmaker.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedDiagnosingMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.card.MaterialCardView
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeDiagnosingMatcher
import java.util.Locale
import kotlin.math.roundToInt

fun hasBackgroundColor(@ColorRes colorResId: Int): Matcher<View> {
    return HasBackgroundColorMatcher(colorResId)
}

fun hasEmptyToolbarSubtitle(): Matcher<View> {
    return HasToolbarSubtitleMatcher("")
}

fun hasToolbarSubtitle(@StringRes subtitleResId: Int): Matcher<View> {
    return HasToolbarSubtitleMatcher(targetContext.getString(subtitleResId))
}

fun hasToolbarTitle(title: String): Matcher<View> {
    return HasToolbarTitleMatcher(title)
}

fun hasToolbarTitle(@StringRes titleResId: Int): Matcher<View> {
    return hasToolbarTitle(targetContext.getString(titleResId))
}

fun hasPreferenceSummary(summary: String): Matcher<View> {
    return hasDescendant(allOf(withId(android.R.id.summary), withText(summary)))
}

fun isFirstTierListImage(): Matcher<View> {
    return isTierListImageAtPosition(0, 0)
}

fun isEmpty(): Matcher<View> {
    return IsEmptyMatcher()
}

fun isPositiveAlertButton(): Matcher<View> {
    return withId(android.R.id.button1)
}

fun isPreferencesRecyclerView(): Matcher<View> {
    return withId(androidx.preference.R.id.recycler_view)
}

fun isSeekbarPreferenceWithTitle(@StringRes titleResId: Int): Matcher<View> {
    return allOf(
        withId(androidx.preference.R.id.seekbar),
        withParent(hasSibling(withChild(allOf(withId(android.R.id.title), withText(titleResId)))))
    )
}

fun isSnackbarAction(): Matcher<View> {
    return withId(com.google.android.material.R.id.snackbar_action)
}

fun isTierAtPosition(position: Int): Matcher<View> {
    return IsTierAtPositionMatcher(position)
}

fun isTierListAtPosition(position: Int): Matcher<View> {
    return IsTierListAtPositionMatcher(position)
}

fun isTierHeaderAtPosition(position: Int): Matcher<View> {
    return allOf(withId(R.id.text_tier), isDescendantOfA(isTierAtPosition(position)))
}

fun isTierListImageAtPosition(tierPosition: Int, imagePosition: Int): Matcher<View> {
    return IsTierListImageAtPositionMatcher(tierPosition, imagePosition)
}

fun scaledWith(scale: Int): Matcher<View> {
    return scaledWithZoomValue(zoomValue = scale - 1)
}

fun scaledWithZoomValue(zoomValue: Int): Matcher<View> {
    val sizePx = (uiDevice.displayWidth / zoomValue.toFloat()).roundToInt()
    return SizeEqualsMatcher(Size(sizePx, sizePx))
}

fun withPreferenceTitle(@StringRes titleResId: Int): Matcher<View> {
    return withChild(allOf(withId(android.R.id.title), withText(titleResId)))
}

fun withTierListImage(image: Image): Matcher<View> {
    return WithTierListImageMatcher(image)
}

fun withTierListImages(images: List<Image>): Matcher<View> {
    return withChild(
        allOf(images.map { withChild(withTierListImage(it)) } + withId(R.id.list_images))
    )
}

fun withTierListTitle(title: String): Matcher<View> {
    return allOf(
        isAssignableFrom(MaterialCardView::class.java),
        hasDescendant(allOf(withId(R.id.text_title), withText(title)))
    )
}

fun withTierListTitle(@StringRes titleResId: Int): Matcher<View> {
    return withTierListTitle(targetContext.getString(titleResId))
}

private class IsEmptyMatcher :
    BoundedDiagnosingMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeMoreTo(description: Description) {
        description
            .appendText("recyclerView has ")
            .appendValue(0)
            .appendValue(" items")
    }

    override fun matchesSafely(item: RecyclerView, mismatchDescription: Description): Boolean {
        val itemCount = item.adapter?.itemCount ?: 0
        mismatchDescription
            .appendText("recyclerView had ")
            .appendValue(itemCount)
            .appendText(" items")

        return itemCount == 0
    }
}

private class IsTierAtPositionMatcher(private val expectedTierPosition: Int) :
    TypeSafeDiagnosingMatcher<View>(View::class.java) {

    override fun describeTo(description: Description) {
        description
            .appendText("tier position is ")
            .appendValue("$expectedTierPosition")
    }

    override fun matchesSafely(item: View, mismatchDescription: Description): Boolean {
        val parent = item.parent
        if (parent !is RecyclerView || parent.id != R.id.list_tiers) {
            mismatchDescription.appendText("view was not a tier")
            return false
        }

        val actualTierPosition = parent.getChildAdapterPosition(item)
        mismatchDescription
            .appendText("tier position was ")
            .appendValue("$actualTierPosition")

        return expectedTierPosition == actualTierPosition
    }
}

private class IsTierListAtPositionMatcher(private val expectedPosition: Int) :
    TypeSafeDiagnosingMatcher<View>(View::class.java) {

    override fun describeTo(description: Description) {
        description
            .appendText("tier list position is ")
            .appendValue("$expectedPosition")
    }

    override fun matchesSafely(item: View, mismatchDescription: Description): Boolean {
        val parent = item.parent
        if (parent !is RecyclerView || parent.id != R.id.list_tier_lists) {
            mismatchDescription.appendText("view was not a tier list")
            return false
        }

        val actualTierPosition = parent.getChildAdapterPosition(item)
        mismatchDescription
            .appendText("tier list position was ")
            .appendValue("$actualTierPosition")

        return expectedPosition == actualTierPosition
    }
}

private class IsTierListImageAtPositionMatcher(
    private val expectedTierPosition: Int,
    private val expectedImagePosition: Int
) : BoundedDiagnosingMatcher<View, ImageView>(ImageView::class.java) {

    override fun describeMoreTo(description: Description) {
        description
            .appendText("imageView position in tier list is ")
            .appendValue("($expectedTierPosition, $expectedImagePosition)")
    }

    override fun matchesSafely(item: ImageView, mismatchDescription: Description): Boolean {
        val tag = item.tag
        if (tag !is ImageDragData) {
            mismatchDescription.appendText("imageView was not found in any tier")
            return false
        }

        val actualTierPosition = tag.tierPosition
        val actualImagePosition = tag.itemPosition

        mismatchDescription
            .appendText("imageView position in tier list was ")
            .appendValue("($expectedTierPosition, $expectedImagePosition)")

        return expectedTierPosition == actualTierPosition
                && expectedImagePosition == actualImagePosition
    }
}

private class HasBackgroundColorMatcher(@ColorRes private val colorResId: Int) :
    TypeSafeDiagnosingMatcher<View>(View::class.java) {

    override fun describeTo(description: Description) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedColor = context.getColor(colorResId)

        description
            .appendText("view backgound color is ")
            .appendValue(getColorHex(expectedColor))
    }

    override fun matchesSafely(item: View, mismatchDescription: Description): Boolean {
        val actualColor = (item.background as? ColorDrawable)?.color
        if (actualColor == null) {
            mismatchDescription.appendText("view.background was not a ColorDrawable")
            return false
        }

        mismatchDescription
            .appendText("view background color was ")
            .appendValue(getColorHex(actualColor))

        val expectedColor = item.context.getColor(colorResId)
        return expectedColor == actualColor
    }

    private fun getColorHex(color: Int): String {
        return String.format(
            Locale.ROOT,
            "#%02X%06X",
            0xFF and Color.alpha(color),
            0xFFFFFF and color
        )
    }
}

private class HasToolbarSubtitleMatcher(private val expectedSubtitle: String) :
    BoundedDiagnosingMatcher<View, Toolbar>(Toolbar::class.java) {

    override fun describeMoreTo(description: Description) {
        description
            .appendText("toolbar.subtitle is ")
            .appendValue(expectedSubtitle)
    }

    override fun matchesSafely(item: Toolbar, mismatchDescription: Description): Boolean {
        val actualTitle = item.subtitle

        mismatchDescription
            .appendText("toolbar.subtitle was ")
            .appendValue(actualTitle)

        return expectedSubtitle == actualTitle
    }
}

private class HasToolbarTitleMatcher(private val expectedTitle: String) :
    BoundedDiagnosingMatcher<View, Toolbar>(Toolbar::class.java) {

    override fun describeMoreTo(description: Description) {
        description
            .appendText("toolbar.title is ")
            .appendValue(expectedTitle)
    }

    override fun matchesSafely(item: Toolbar, mismatchDescription: Description): Boolean {
        val actualTitle = item.title

        mismatchDescription
            .appendText("toolbar.title was ")
            .appendValue(actualTitle)

        return expectedTitle == actualTitle
    }
}

private class SizeEqualsMatcher(private val expectedSize: Size) :
    TypeSafeDiagnosingMatcher<View>(View::class.java) {

    override fun describeTo(description: Description) {
        description
            .appendText("view size is ")
            .appendValue(expectedSize)
            .appendValue(" pixels")
    }

    override fun matchesSafely(item: View, mismatchDescription: Description): Boolean {
        val actualSize = Size(item.width, item.height)

        mismatchDescription
            .appendText("view size was ")
            .appendValue(actualSize)
            .appendValue(" pixels")

        return expectedSize == actualSize
    }
}

private class WithTierListImageMatcher(private val expectedImage: Image) :
    BoundedDiagnosingMatcher<View, ImageView>(ImageView::class.java) {

    override fun describeMoreTo(description: Description) {
        description
            .appendText("imageView image is ")
            .appendValue(expectedImage)
    }

    override fun matchesSafely(item: ImageView, mismatchDescription: Description): Boolean {
        val tag = item.tag
        if (tag !is ImageDragData) {
            mismatchDescription.appendText("imageView was not found in any tier")
            return false
        }

        val actualImage = tag.image
        mismatchDescription
            .appendText("imageView image was ")
            .appendValue(actualImage)

        return expectedImage == actualImage
    }
}