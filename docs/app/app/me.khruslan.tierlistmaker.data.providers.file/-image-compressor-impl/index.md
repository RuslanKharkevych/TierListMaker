//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[ImageCompressorImpl](index.md)

# ImageCompressorImpl

class [ImageCompressorImpl](index.md)@Injectconstructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [ImageCompressor](../-image-compressor/index.md)

[ImageCompressor](../-image-compressor/index.md) implementation.

Implemented with [Compressor](https://github.com/Shouheng88/Compressor) library. Moves the compression to the background thread. Traces the performance of the compression.

## Constructors

| | |
|---|---|
| [ImageCompressorImpl](-image-compressor-impl.md) | @Inject<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md))<br>Creates a new image compressor instance. |

## Types

| Name | Summary |
|---|---|
| [ImageCompressorException](-image-compressor-exception/index.md) | private class [ImageCompressorException](-image-compressor-exception/index.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Thrown when image compression fails. |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Application context. |
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides IO dispatcher. |
| [imageSize](image-size.md) | private val [imageSize](image-size.md): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Maximum size of the compressed image. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md) |
| [preferencesHelper](preferences-helper.md) | private val [preferencesHelper](preferences-helper.md): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Reads image quality preference. |

## Functions

| Name | Summary |
|---|---|
| [compress](compress.md) | open suspend override fun [compress](compress.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)<br>Compresses the image from Uri and saves the resulting file into the target directory. |
| [getImageQuality](get-image-quality.md) | private suspend fun [getImageQuality](get-image-quality.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Asynchronously fetches image quality from user preferences. |
