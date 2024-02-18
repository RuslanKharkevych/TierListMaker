//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[ReorderableCallback](index.md)/[onMove](on-move.md)

# onMove

open override fun [onMove](on-move.md)(recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html), viewHolder: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), target: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Called when [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) wants to move the dragged item from its old position to the new position.

Invokes [Reorderable.onItemMove](../-reorderable/on-item-move.md) callback.

#### Return

Always returns true, assuming that the [viewHolder](on-move.md) has been moved to the adapter position of [target](on-move.md).

#### Parameters

| | |
|---|---|
| recyclerView | The recycler view to which [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) is attached to. |
| viewHolder | The view holder which is being dragged by the user. |
| target | The view holder over which the currently active item is being dragged. |
