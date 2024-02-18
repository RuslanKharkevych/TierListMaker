//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBitmapGeneratorImpl](index.md)

# TierListBitmapGeneratorImpl

class [TierListBitmapGeneratorImpl](index.md)@Injectconstructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [TierListBitmapGenerator](../-tier-list-bitmap-generator/index.md)

[TierListBitmapGenerator](../-tier-list-bitmap-generator/index.md) implementation.

Moves generation to the background thread. Traces performance of the generation.

## Constructors

| | |
|---|---|
| [TierListBitmapGeneratorImpl](-tier-list-bitmap-generator-impl.md) | @Inject<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md))<br>Creates tier list bitmap generator with injected dependencies. |

## Properties

| Name | Summary |
|---|---|
| [cellSize](cell-size.md) | private val [cellSize](cell-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Size of the single image (column width) in the bitmap. |
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Application context. |
| [darkIconPaint](dark-icon-paint.md) | private val [darkIconPaint](dark-icon-paint.md): [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html)<br>Paint for the dark icons. |
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides default dispatcher. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Traces generation performance. |
| [preferencesHelper](preferences-helper.md) | private val [preferencesHelper](preferences-helper.md): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Reads application theme preference. |
| [textPaint](text-paint.md) | private val [textPaint](text-paint.md): [Paint](https://developer.android.com/reference/kotlin/android/graphics/Paint.html)<br>Paint for the tier title text. |

## Functions

| Name | Summary |
|---|---|
| [createBackgroundBitmap](create-background-bitmap.md) | private fun [createBackgroundBitmap](create-background-bitmap.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)<br>Creates background bitmap for the given tier list. |
| [createBackgroundCanvas](create-background-canvas.md) | private fun [createBackgroundCanvas](create-background-canvas.md)(bitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), nightModeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html)<br>Creates background canvas with the size of the provided [bitmap](create-background-canvas.md). |
| [drawTierHeader](draw-tier-header.md) | private fun [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).[drawTierHeader](draw-tier-header.md)(tier: [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md), zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cursorPosition: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html))<br>Draws tier header on the canvas. |
| [drawTierImage](draw-tier-image.md) | private fun [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).[drawTierImage](draw-tier-image.md)(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), cursorPosition: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html), nightModeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Draws tier image on the canvas. |
| [drawTierTitle](draw-tier-title.md) | private fun [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).[drawTierTitle](draw-tier-title.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Draws tier title on the canvas. |
| [generate](generate.md) | open suspend override fun [generate](generate.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)<br>Generates bitmap from tier list. |
| [getRowsCount](get-rows-count.md) | private fun [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md).[getRowsCount](get-rows-count.md)(zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Calculates number of rows that [Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md) takes. |
| [loadImage](load-image.md) | private fun RequestBuilder&lt;[Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)&gt;.[loadImage](load-image.md)(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)): RequestBuilder&lt;[Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)&gt;<br>Loads image from file or resource depending on the image type. |
| [toBitmap](to-bitmap.md) | private fun [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md).[toBitmap](to-bitmap.md)(): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)<br>Converts image to the bitmap. |
