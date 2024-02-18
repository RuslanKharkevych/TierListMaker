//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)/[createBackgroundBitmap](create-background-bitmap.md)

# createBackgroundBitmap

private fun [createBackgroundBitmap](create-background-bitmap.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)

Creates background bitmap for the given tier list.

The size of the bitmap is calculated based [cellSize](cell-size.md) and the number of rows and columns that tier list takes.

#### Return

Created bitmap.

#### Parameters

| | |
|---|---|
| tierList | Tier list which contents are used for bitmap size calculations. |
