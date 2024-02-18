//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[FileManager](index.md)/[provideContentUriFromBitmap](provide-content-uri-from-bitmap.md)

# provideContentUriFromBitmap

abstract suspend fun [provideContentUriFromBitmap](provide-content-uri-from-bitmap.md)(bitmap: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html), fileName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?

Returns a content URI for a file, created from bitmap.

#### Return

Content URI of the created file or null in case of error.

#### Parameters

| | |
|---|---|
| bitmap | Bitmap that should be written to file. |
| fileName | The name of the file to be created. |
