//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[ImageCompressor](index.md)

# ImageCompressor

interface [ImageCompressor](index.md)

Compressor of image files.

The compressing is asynchronous. The subclasses must ensure that it can be safely called from the main thread.

#### Inheritors

| |
|---|
| [ImageCompressorImpl](../-image-compressor-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [compress](compress.md) | abstract suspend fun [compress](compress.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)<br>Compresses the image from Uri and saves the resulting file into the target directory. |
