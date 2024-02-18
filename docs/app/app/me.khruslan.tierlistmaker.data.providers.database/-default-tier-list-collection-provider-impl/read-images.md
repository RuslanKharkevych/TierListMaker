//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProviderImpl](index.md)/[readImages](read-images.md)

# readImages

private fun [readImages](read-images.md)(folderName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Reads all assets from the provided folder.

A folder must exists in [ASSETS_RELATIVE_PATH](-constants/-a-s-s-e-t-s_-r-e-l-a-t-i-v-e_-p-a-t-h.md) and must not be empty. Make sure that it contains only images because all files from the folder will be read.

#### Return

List of image file names.

#### Parameters

| | |
|---|---|
| folderName | Name of the folder to read assets from. |

#### Throws

| | |
|---|---|
| [IOException](https://developer.android.com/reference/kotlin/java/io/IOException.html) | When I/O error happens while listing assets or folder is empty. |
