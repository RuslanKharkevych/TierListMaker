package me.khruslan.tierlistmaker.utils

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import org.hamcrest.Matcher

private const val DRAG_STEPS = 50

fun ViewInteraction.dragTo(viewMatcher: Matcher<View>): ViewInteraction {
    return dragTo(viewMatcher, TouchPosition.Center)
}

fun ViewInteraction.dragToBottomOf(viewMatcher: Matcher<View>): ViewInteraction {
    return dragTo(viewMatcher, TouchPosition.Bottom)
}

fun ViewInteraction.dragToTopOf(viewMatcher: Matcher<View>): ViewInteraction {
    return dragTo(viewMatcher, TouchPosition.Top)
}

fun ViewInteraction.fetchTierListTitleAtPosition(position: Int): String {
    val recylerView = getView() as RecyclerView
    val layoutManager = checkNotNull(recylerView.layoutManager)
    val itemView = checkNotNull(layoutManager.findViewByPosition(position))
    val titleView = itemView.findViewById<TextView>(R.id.text_title)
    return titleView.text.toString()
}

fun ViewInteraction.fetchTierListImage(): Image {
    return (getView().tag as ImageDragData).image
}

fun ViewInteraction.fetchTierListImages(): List<Image> {
    val listImages = getView().findViewById<RecyclerView>(R.id.list_images)
    return listImages.children.map { (it.tag as ImageDragData).image }.toList()
}

private fun ViewInteraction.dragTo(
    viewMatcher: Matcher<View>,
    targetTouchPosition: TouchPosition
): ViewInteraction {
    val sourceLocation = fetchLocationOnScreen()
    val targetLocation = onView(viewMatcher).fetchLocationOnScreen(targetTouchPosition)

    uiDevice.drag(
        sourceLocation[0],
        sourceLocation[1],
        targetLocation[0],
        targetLocation[1],
        DRAG_STEPS
    )

    return this
}

private fun ViewInteraction.fetchLocationOnScreen(
    touchPosition: TouchPosition = TouchPosition.Center
): IntArray {
    val view = getView()
    val location = IntArray(2)

    view.getLocationOnScreen(location)
    location[0] += view.width / 2
    location[1] += when (touchPosition) {
        TouchPosition.Bottom -> view.height
        TouchPosition.Center -> view.height / 2
        TouchPosition.Top -> 0
    }

    return location
}

private fun ViewInteraction.getView(): View {
    var view: View? = null
    var exception: NoMatchingViewException? = null

    check { v, e ->
        view = v
        exception = e
    }

    return view ?: throw exception!!
}

private enum class TouchPosition {
    Bottom,
    Center,
    Top
}