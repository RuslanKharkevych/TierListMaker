//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierHolder](index.md)

# TierHolder

class [TierHolder](index.md)(val binding: ItemTierBinding, val dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)

Holder of the tier view.

The tier view includes header and nested recycler view with images.

## Constructors

| | |
|---|---|
| [TierHolder](-tier-holder.md) | constructor(binding: ItemTierBinding, dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html))<br>Creates a new holder instance. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): ItemTierBinding<br>Binding of the tier item view. |
| [dragListener](drag-listener.md) | private val [dragListener](drag-listener.md): [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)<br>Listener of tier list drag events. |

## Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | fun [bind](bind.md)(tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md), imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [TierDragData](../../me.khruslan.tierlistmaker.data.models.drag/-tier-drag-data/index.md))<br>Binds tier data to the item view. |
| [setOnDragListener](set-on-drag-listener.md) | private fun [setOnDragListener](set-on-drag-listener.md)()<br>Registers a drag event listener callback object for the tier images view. |
