//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)/[drawTierHeader](draw-tier-header.md)

# drawTierHeader

private fun [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).[drawTierHeader](draw-tier-header.md)(tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md), zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cursorPosition: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html))

Draws tier header on the canvas.

#### Receiver

The canvas that hosts draw calls.

#### Parameters

| | |
|---|---|
| tier | Tier which header should be drawn. |
| zoomValue | [TierList.zoomValue](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/zoom-value.md) used to [getRowsCount](get-rows-count.md). |
| cursorPosition | Position of the tier header on canvas. |
