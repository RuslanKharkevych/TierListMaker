//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListPreviewHolder](index.md)

# TierListPreviewHolder

class [TierListPreviewHolder](index.md)(val binding: ItemTierListPreviewBinding, val onClick: (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)

View holder of the tier list preview.

Tier list preview is a card with a title and three images.

## Constructors

| | |
|---|---|
| [TierListPreviewHolder](-tier-list-preview-holder.md) | constructor(binding: ItemTierListPreviewBinding, onClick: (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Creates a new holder instance. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): ItemTierListPreviewBinding<br>Binding of the tier list preview. |
| [onClick](on-click.md) | private val [onClick](on-click.md): (position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Item click listener. |

## Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | fun [bind](bind.md)(preview: [TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md))<br>Binds tier list preview to the item view. |
| [bindImage](bind-image.md) | private fun [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html).[bindImage](bind-image.md)(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)?)<br>Binds image to the image view. |
| [setOnClickListener](set-on-click-listener.md) | private fun [setOnClickListener](set-on-click-listener.md)()<br>Initializes the item click listener. |
