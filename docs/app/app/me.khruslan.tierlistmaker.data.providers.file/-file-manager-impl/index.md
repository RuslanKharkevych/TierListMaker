//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[FileManagerImpl](index.md)

# FileManagerImpl

class [FileManagerImpl](index.md) @Inject constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val imageCompressor: [ImageCompressor](../-image-compressor/index.md), val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [FileManager](../-file-manager/index.md)

[FileManager](../-file-manager/index.md) implementation.

Moves all operations to the background thread. Collects performance traces. No manifest permissions are required to use this class.

## Constructors

| | |
|---|---|
| [FileManagerImpl](-file-manager-impl.md) | @Inject <br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), imageCompressor: [ImageCompressor](../-image-compressor/index.md), dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md))<br>Creates a new file manager instance. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>File manager constants for internal use. |
| [FileManagerException](-file-manager-exception/index.md) | private class [FileManagerException](-file-manager-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Thrown on I/O errors caught inside the file manager. |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Application context. |
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides Dispatchers.IO context. |
| [imageCompressor](image-compressor.md) | private val [imageCompressor](image-compressor.md): [ImageCompressor](../-image-compressor/index.md)<br>Compresses image files. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Traces performance of the operations. |

## Functions

| Name | Summary |
|---|---|
| [createImageFileFromUri](create-image-file-from-uri.md) | open suspend override fun [createImageFileFromUri](create-image-file-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)?<br>Saves image file from URI into the external pictures directory. |
| [getContentUri](get-content-uri.md) | private fun [File](https://developer.android.com/reference/kotlin/java/io/File.html).[getContentUri](get-content-uri.md)(): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)<br>Returns a content URI for the file. |
| [getPicturesDirectory](get-pictures-directory.md) | private suspend fun [getPicturesDirectory](get-pictures-directory.md)(): [File](https://developer.android.com/reference/kotlin/java/io/File.html)<br>Returns directory for saving pictures. |
| [provideContentUriFromBitmap](provide-content-uri-from-bitmap.md) | open suspend override fun [provideContentUriFromBitmap](provide-content-uri-from-bitmap.md)(bitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), fileName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?<br>Returns a content URI for a file, created from bitmap. |
| [writeToFile](write-to-file.md) | private fun [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html).[writeToFile](write-to-file.md)(file: [File](https://developer.android.com/reference/kotlin/java/io/File.html))<br>Writes bitmap to a given file. |
