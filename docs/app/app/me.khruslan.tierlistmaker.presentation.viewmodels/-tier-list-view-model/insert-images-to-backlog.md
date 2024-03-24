//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[insertImagesToBacklog](insert-images-to-backlog.md)

# insertImagesToBacklog

fun [insertImagesToBacklog](insert-images-to-backlog.md)(images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)

Inserts images at the start of the backlog.

If the list of images is empty, completes without any action. Updates [tierListEvent](tier-list-event.md) with [BacklogImagesAdded](../../me.khruslan.tierlistmaker.data.models.tierlist/-backlog-images-added/index.md) and logs [ImagesAdded](../../me.khruslan.tierlistmaker.util.analytics/-images-added/index.md) analytic event.

#### Parameters

| | |
|---|---|
| images | Images to insert. |
