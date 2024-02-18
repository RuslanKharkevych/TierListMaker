//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](index.md)/[enableReordering](enable-reordering.md)

# enableReordering

fun [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html).[enableReordering](enable-reordering.md)()

Attaches [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) with [ReorderableCallback](-reorderable-callback/index.md) to the [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html).

This function requires that [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html) has set [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) that implements [Reorderable](-reorderable/index.md) interface. Enables both changing the order of items and swipe-to-dismiss functionality.

#### Receiver

Recycler view for which reordering will be enabled.
