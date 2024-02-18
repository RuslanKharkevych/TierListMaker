//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable](../index.md)/[Reorderable](index.md)

# Reorderable

interface [Reorderable](index.md)

Ability of [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) that allows to move and/or swipe its items.

In order to enable drag and drop functionality in your [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html) follow the steps below:

- [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html) must implement [Reorderable](index.md).
- [ItemTouchHelper](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/ItemTouchHelper.html) with [ReorderableCallback](../-reorderable-callback/index.md) must be attached to [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html).

#### Inheritors

| |
|---|
| [TierAdapter](../../me.khruslan.tierlistmaker.presentation.adapters/-tier-adapter/index.md) |
| [TierListPreviewAdapter](../../me.khruslan.tierlistmaker.presentation.adapters/-tier-list-preview-adapter/index.md) |

## Functions

| Name | Summary |
|---|---|
| [onItemMove](on-item-move.md) | abstract fun [onItemMove](on-item-move.md)(fromPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Notifies that item was moved. |
| [onItemSwiped](on-item-swiped.md) | abstract fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Notifies that item was swiped. |
