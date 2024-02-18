//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[FileManager](index.md)

# FileManager

interface [FileManager](index.md)

Manager for working with files.

All functions are asynchronous. The subclasses must ensure that all functions can be safely called from the main thread. All exceptions must be handled internally. The result of the operation is returned as a nullable type.

#### Inheritors

| |
|---|
| [FileManagerImpl](../-file-manager-impl/index.md) |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | object [Constants](-constants/index.md)<br>MIME types and file extensions. |

## Functions

| Name | Summary |
|---|---|
| [createImageFileFromUri](create-image-file-from-uri.md) | abstract suspend fun [createImageFileFromUri](create-image-file-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)?<br>Creates image file from URI and saves it in the file system. |
| [provideContentUriFromBitmap](provide-content-uri-from-bitmap.md) | abstract suspend fun [provideContentUriFromBitmap](provide-content-uri-from-bitmap.md)(bitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), fileName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?<br>Returns a content URI for a file, created from bitmap. |
