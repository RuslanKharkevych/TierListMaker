//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](index.md)

# Package-level declarations

Recycler view holders.

## Types

| Name | Summary |
|---|---|
| [TierHolder](-tier-holder/index.md) | class [TierHolder](-tier-holder/index.md)(val binding: ItemTierBinding, val dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)<br>Holder of the tier view. |
| [TierListImageHolder](-tier-list-image-holder/index.md) | class [TierListImageHolder](-tier-list-image-holder/index.md)(val imageView: [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html), dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), [View.OnTouchListener](https://developer.android.com/reference/kotlin/android/view/View.OnTouchListener.html)<br>Holder of the tier list image. |
| [TierListPreviewHolder](-tier-list-preview-holder/index.md) | class [TierListPreviewHolder](-tier-list-preview-holder/index.md)(val binding: ItemTierListPreviewBinding, val onClick: (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)<br>View holder of the tier list preview. |
