//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](index.md)

# Package-level declarations

UI utilities for reordering items in recycler view.

## Types

| Name | Summary |
|---|---|
| [Reorderable](-reorderable/index.md) | interface [Reorderable](-reorderable/index.md)<br>Ability of [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) that allows to move and/or swipe its items. |
| [ReorderableCallback](-reorderable-callback/index.md) | class [ReorderableCallback](-reorderable-callback/index.md)(val reorderable: [Reorderable](-reorderable/index.md)) : [ItemTouchHelper.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.Callback.html)<br>Class that delegates [ItemTouchHelper.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.Callback.html) events to the provided [Reorderable](-reorderable/index.md). |

## Functions

| Name | Summary |
|---|---|
| [enableReordering](enable-reordering.md) | fun [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html).[enableReordering](enable-reordering.md)()<br>Attaches [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) with [ReorderableCallback](-reorderable-callback/index.md) to the [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html). |
