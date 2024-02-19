//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../index.md)/[FileManagerImpl](index.md)/[getPicturesDirectory](get-pictures-directory.md)

# getPicturesDirectory

private suspend fun [getPicturesDirectory](get-pictures-directory.md)(): [File](https://developer.android.com/reference/kotlin/java/io/File.html)

Returns directory for saving pictures.

If shared storage is not available, falls back to internal storage.

#### Return

The absolute path to the directory on the filesystem where pictures can be saved.
