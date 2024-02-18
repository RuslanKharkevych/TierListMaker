//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[ReorderableCallback](index.md)

# ReorderableCallback

class [ReorderableCallback](index.md)(val reorderable: [Reorderable](../-reorderable/index.md)) : [ItemTouchHelper.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.Callback.html)

Class that delegates [ItemTouchHelper.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.Callback.html) events to the provided [Reorderable](../-reorderable/index.md).

Up and down drag flags and right swipe flag are enabled. Therefore, it should be used in vertically scrolled lists only. In order to enable drag and drop functionality in your [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html) follow the steps below:

- [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) must implement [Reorderable](../-reorderable/index.md).
- [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) with [ReorderableCallback](index.md) must be attached to [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html).

## Constructors

| | |
|---|---|
| [ReorderableCallback](-reorderable-callback.md) | constructor(reorderable: [Reorderable](../-reorderable/index.md))<br>Creates a new reorderable callback |

## Properties

| Name | Summary |
|---|---|
| [reorderable](reorderable.md) | private val [reorderable](reorderable.md): [Reorderable](../-reorderable/index.md)<br>Reorderable that will receive drag and drop events. |

## Functions

| Name | Summary |
|---|---|
| [getMovementFlags](get-movement-flags.md) | open override fun [getMovementFlags](get-movement-flags.md)(recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html), viewHolder: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns a composite flag which defines the enabled move directions in each state (idle, swiping, dragging). |
| [onMove](on-move.md) | open override fun [onMove](on-move.md)(recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html), viewHolder: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), target: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Called when [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) wants to move the dragged item from its old position to the new position. |
| [onSwiped](on-swiped.md) | open override fun [onSwiped](on-swiped.md)(viewHolder: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), direction: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Called when a view holder is swiped by the user. |
