//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierAdapter](index.md)

# TierAdapter

class [TierAdapter](index.md)(val tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;, val dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html), var imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val onTierRemoved: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[TierHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-holder/index.md)&gt; , [Reorderable](../../me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable/-reorderable/index.md)

Adapter that manages tiers in the tier list.

Allows to reorder and remove tiers.

## Constructors

| | |
|---|---|
| [TierAdapter](-tier-adapter.md) | constructor(tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;, dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html), imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), onTierRemoved: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Creates a new adapter instance. |

## Properties

| Name | Summary |
|---|---|
| [dragListener](drag-listener.md) | private val [dragListener](drag-listener.md): [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)<br>Listener of tier list drag events. |
| [imageSize](image-size.md) | private var [imageSize](image-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Initial image size. |
| [onTierRemoved](on-tier-removed.md) | private val [onTierRemoved](on-tier-removed.md): (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when tier has been swiped by user. |
| [tiers](tiers.md) | private val [tiers](tiers.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;<br>List of tiers. |

## Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | open override fun [getItemCount](get-item-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the total number of tiers. |
| [onBindViewHolder](on-bind-view-holder.md) | open override fun [onBindViewHolder](on-bind-view-holder.md)(holder: [TierHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Binds tier at given position. |
| [onCreateViewHolder](on-create-view-holder.md) | open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [TierHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-holder/index.md)<br>Creates a new tier holder instance. |
| [onItemMove](on-item-move.md) | open override fun [onItemMove](on-item-move.md)(fromPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Swap tiers and notifies that data set has changed. |
| [onItemSwiped](on-item-swiped.md) | open override fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Removes tier at given position and notifies that data set has changed. |
| [swapTierStyles](swap-tier-styles.md) | private fun [swapTierStyles](swap-tier-styles.md)(firstTierIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), secondTierIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Swaps styles of tiers without altering other contents. |
| [updateImageSize](update-image-size.md) | fun [updateImageSize](update-image-size.md)(imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Updates the image size and notifies that data set has changed. |
