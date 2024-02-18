//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierListImageAdapter](index.md)

# TierListImageAdapter

class [TierListImageAdapter](index.md)(val images: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;, val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), var imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[TierListImageHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-image-holder/index.md)&gt; 

Adapter that manages images in tier or backlog.

Allows to drag and drop images.

## Constructors

| | |
|---|---|
| [TierListImageAdapter](-tier-list-image-adapter.md) | constructor(images: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;, tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html))<br>Creates a new adapter instance. |

## Properties

| Name | Summary |
|---|---|
| [dragListener](drag-listener.md) | private val [dragListener](drag-listener.md): [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)<br>Listener of tier list drag events. |
| [images](images.md) | private val [images](images.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;<br>List of images. |
| [imageSize](image-size.md) | private var [imageSize](image-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Initial image size. |
| [tierPosition](tier-position.md) | private val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier or [BACKLOG_TIER_POSITION](../../me.khruslan.tierlistmaker.util/-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md) if it's backlog. |

## Functions

| Name | Summary |
|---|---|
| [createTag](create-tag.md) | private fun [createTag](create-tag.md)(itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)<br>Creates unique tag for the view based on its position. |
| [getItemCount](get-item-count.md) | open override fun [getItemCount](get-item-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the total number of images. |
| [onBindViewHolder](on-bind-view-holder.md) | open override fun [onBindViewHolder](on-bind-view-holder.md)(holder: [TierListImageHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-image-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Binds image at given position. |
| [onCreateViewHolder](on-create-view-holder.md) | open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [TierListImageHolder](../../me.khruslan.tierlistmaker.presentation.holders/-tier-list-image-holder/index.md)<br>Creates a new tier list image holder instance. |
| [updateImageSize](update-image-size.md) | fun [updateImageSize](update-image-size.md)(imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Updates the image size and notifies that data set has changed. |
