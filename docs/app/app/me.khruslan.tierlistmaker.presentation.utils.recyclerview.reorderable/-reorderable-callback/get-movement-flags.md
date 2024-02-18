//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[ReorderableCallback](index.md)/[getMovementFlags](get-movement-flags.md)

# getMovementFlags

open override fun [getMovementFlags](get-movement-flags.md)(recyclerView: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html), viewHolder: [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Returns a composite flag which defines the enabled move directions in each state (idle, swiping, dragging).

Allows the items to be drag & dropped vertically and swiped left to be dismissed,

#### Return

Flags specifying which movements are allowed on this view holder.

#### Parameters

| | |
|---|---|
| recyclerView | The recycler view to which [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) is attached. |
| viewHolder | The view holder for which the movement information is necessary. |
