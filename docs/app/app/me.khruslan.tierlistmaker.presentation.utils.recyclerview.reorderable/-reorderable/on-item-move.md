//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[Reorderable](index.md)/[onItemMove](on-item-move.md)

# onItemMove

abstract fun [onItemMove](on-item-move.md)(fromPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Notifies that item was moved.

To apply changes, swap items and call [RecyclerView.Adapter.notifyItemMoved](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html#notifyitemmoved).

#### Parameters

| | |
|---|---|
| fromPosition | Initial position of the item. |
| toPosition | Target position of the item. |
