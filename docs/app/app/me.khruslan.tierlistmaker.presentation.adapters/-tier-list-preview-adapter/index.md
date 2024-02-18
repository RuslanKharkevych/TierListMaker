//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierListPreviewAdapter](index.md)

# TierListPreviewAdapter

class [TierListPreviewAdapter](index.md)(val tierListPreviews: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;, val onClick: (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onPreviewMoved: (from: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), to: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onPreviewSwiped: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[TierListPreviewHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-preview-holder/index.md)&gt; , [Reorderable](../../me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable/-reorderable/index.md)

Adapter that manages tier list previews.

Allows to reorder and remove previews.

## Constructors

| | |
|---|---|
| [TierListPreviewAdapter](-tier-list-preview-adapter.md) | constructor(tierListPreviews: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;, onClick: (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onPreviewMoved: (from: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), to: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onPreviewSwiped: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Creates a new adapter instance. |

## Properties

| Name | Summary |
|---|---|
| [onClick](on-click.md) | private val [onClick](on-click.md): (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Item click listener. |
| [onPreviewMoved](on-preview-moved.md) | private val [onPreviewMoved](on-preview-moved.md): (from: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), to: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when preview has been moved by user. |
| [onPreviewSwiped](on-preview-swiped.md) | private val [onPreviewSwiped](on-preview-swiped.md): (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when preview has been swiped by user. |
| [tierListPreviews](tier-list-previews.md) | private val [tierListPreviews](tier-list-previews.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;<br>List of tier list previews. |

## Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | open override fun [getItemCount](get-item-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the total number of previews. |
| [onBindViewHolder](on-bind-view-holder.md) | open override fun [onBindViewHolder](on-bind-view-holder.md)(holder: [TierListPreviewHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-preview-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Binds preview at given position. |
| [onCreateViewHolder](on-create-view-holder.md) | open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [TierListPreviewHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-preview-holder/index.md)<br>Creates a new tier list preview holder instance. |
| [onItemMove](on-item-move.md) | open override fun [onItemMove](on-item-move.md)(fromPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Swap previews and notifies that data set has changed. |
| [onItemSwiped](on-item-swiped.md) | open override fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Invokes [onPreviewSwiped](on-preview-swiped.md) callback. |
| [removePreview](remove-preview.md) | fun [removePreview](remove-preview.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Removes preview by position and notifies that data set has changed. |
