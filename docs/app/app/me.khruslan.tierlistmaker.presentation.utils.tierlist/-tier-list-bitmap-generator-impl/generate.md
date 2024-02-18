//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)/[generate](generate.md)

# generate

open suspend override fun [generate](generate.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)

Generates bitmap from tier list.

The algorithm steps:

1. Calculate canvas size and draw background.
2. Check if there is the next tier in this tier list. If not - move to step 8.
3. Move cursor to the start of the next tier.
4. Calculate tier header size and draw it.
5. Check if there is the next image in this tier. If not - move to step 2.
6. Determine the next image position and move cursor there.
7. Draw tier list image and move to step 5.
8. Finish drawing and return resulting bitmap.

The generation is traced with [GenerateBitmapFromTierListTrace](../../me.khruslan.tierlistmaker.util.performance/-generate-bitmap-from-tier-list-trace/index.md).

#### Return

Generated bitmap.

#### Parameters

| | |
|---|---|
| tierList | Tier list to process. |
