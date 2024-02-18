//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)/[drawTierImage](draw-tier-image.md)

# drawTierImage

private fun [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).[drawTierImage](draw-tier-image.md)(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), cursorPosition: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), nightModeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Draws tier image on the canvas.

If the image is an instance of [ResourceImage](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-resource-image/index.md), its tint is determined by [nightModeEnabled](draw-tier-image.md) flag.

#### Receiver

The canvas that hosts draw calls.

#### Parameters

| | |
|---|---|
| image | Image to draw. |
| cursorPosition | Position of the tier image on canvas. |
| nightModeEnabled | Whether dark theme is used. |
