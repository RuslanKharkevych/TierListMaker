//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)/[createBackgroundCanvas](create-background-canvas.md)

# createBackgroundCanvas

private fun [createBackgroundCanvas](create-background-canvas.md)(bitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), nightModeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html)

Creates background canvas with the size of the provided [bitmap](create-background-canvas.md).

Background color is determined by [nightModeEnabled](create-background-canvas.md) flag.

#### Return

Created canvas.

#### Parameters

| | |
|---|---|
| bitmap | Bitmap for the canvas to draw into. |
| nightModeEnabled | Whether dark theme is used. |
