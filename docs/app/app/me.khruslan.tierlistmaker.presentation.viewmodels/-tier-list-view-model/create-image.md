//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[createImage](create-image.md)

# createImage

private fun [createImage](create-image.md)(file: [File](https://developer.android.com/reference/kotlin/java/io/File.html)?): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)

Creates image from file.

Normally creates [StorageImage](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-storage-image/index.md), bu in case file is null, creates &quot;broken&quot; [ResourceImage](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-resource-image/index.md).

#### Return

Created image.

#### Parameters

| | |
|---|---|
| file | Image file or null. |
