//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[Reorderable](index.md)/[onItemSwiped](on-item-swiped.md)

# onItemSwiped

abstract fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Notifies that item was swiped.

To apply changes, remove the item and call [RecyclerView.Adapter.notifyItemRemoved](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html#notifyitemremoved).

#### Parameters

| | |
|---|---|
| position | Position of the item. |
