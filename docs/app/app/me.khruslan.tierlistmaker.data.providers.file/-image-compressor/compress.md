//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[ImageCompressor](index.md)/[compress](compress.md)

# compress

abstract suspend fun [compress](compress.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)

Compresses the image from Uri and saves the resulting file into the target directory.

The quality of the image depends on user preference. Throws exception on error.

#### Return

Compressed file.

#### Parameters

| | |
|---|---|
| uri | URI of the image to compress. |
| targetDir | Target directory path. |
