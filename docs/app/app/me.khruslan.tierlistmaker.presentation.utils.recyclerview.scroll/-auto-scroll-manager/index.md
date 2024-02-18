//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollManager](index.md)

# AutoScrollManager

class [AutoScrollManager](index.md)(val recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html))

Manager that performs automatic scrolling of the attached [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html) when dragged image is moved to the vertical edge of the visible tier list area.

Recycler view must have [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) and [LinearLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/LinearLayoutManager.html) already set before creating [AutoScrollManager](index.md) instance.

## Constructors

| | |
|---|---|
| [AutoScrollManager](-auto-scroll-manager.md) | constructor(recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html))<br>Creates a new auto scroll manager for the supplied recycler view. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [recyclerView](recycler-view.md) | private val [recyclerView](recycler-view.md): [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html)<br>Attached recycler view. |
| [scrollHelper](scroll-helper.md) | private val [scrollHelper](scroll-helper.md): [AutoScrollHelper](../-auto-scroll-helper/index.md)<br>Helper for computing scroll state. |
| [scrollListener](scroll-listener.md) | private val [scrollListener](scroll-listener.md): [RecyclerView.OnScrollListener](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.OnScrollListener.html)<br>[RecyclerView.OnScrollListener](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.OnScrollListener.html) that is attached to [recyclerView](recycler-view.md). |
| [scrollOffsetPx](scroll-offset-px.md) | private val [scrollOffsetPx](scroll-offset-px.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Unsigned offset in pixels of a single scrolling step. |
| [scrollState](scroll-state.md) | private var [scrollState](scroll-state.md): [AutoScrollState](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/index.md)<br>Current state of automatic scrolling. |

## Functions

| Name | Summary |
|---|---|
| [computeScrollState](compute-scroll-state.md) | private fun [computeScrollState](compute-scroll-state.md)(dragLocation: [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?): [AutoScrollState](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/index.md)<br>Computes [AutoScrollState](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/index.md) based on drag location. |
| [getScrollOffset](get-scroll-offset.md) | private fun [getScrollOffset](get-scroll-offset.md)(direction: [AutoScrollDirection](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-direction/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns scroll offset for the given direction. |
| [performScrollIfNeeded](perform-scroll-if-needed.md) | private fun [performScrollIfNeeded](perform-scroll-if-needed.md)()<br>If [scrollState](scroll-state.md) is [AutoScrollState.Scrolling](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/-scrolling/index.md) performs one-time smooth scroll to the appropriate direction. |
| [stopScrolling](stop-scrolling.md) | fun [stopScrolling](stop-scrolling.md)()<br>Stops automatic scrolling. |
| [updateDragLocation](update-drag-location.md) | fun [updateDragLocation](update-drag-location.md)(dragLocation: [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?)<br>Updates current drag location. |
