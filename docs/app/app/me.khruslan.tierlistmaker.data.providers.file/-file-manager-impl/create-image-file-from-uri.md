//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[FileManagerImpl](index.md)/[createImageFileFromUri](create-image-file-from-uri.md)

# createImageFileFromUri

open suspend override fun [createImageFileFromUri](create-image-file-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)?

Saves image file from URI into the external pictures directory.

Note that the image is compressed before it's saved.

#### Return

Created file or null in case of error.

#### Parameters

| | |
|---|---|
| uri | URI of the image file. |
