//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGenerator](index.md)

# TierListBitmapGenerator

interface [TierListBitmapGenerator](index.md)

Tool that allows to generate [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html) from [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).

The generation is asynchronous. The subclasses must ensure that it can be safely called from the main thread.

#### Inheritors

| |
|---|
| [TierListBitmapGeneratorImpl](../-tier-list-bitmap-generator-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [generate](generate.md) | abstract suspend fun [generate](generate.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)<br>Generates bitmap from tier list. |
