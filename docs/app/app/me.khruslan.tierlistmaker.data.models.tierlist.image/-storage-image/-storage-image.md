//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../index.md)/[StorageImage](index.md)/[StorageImage](-storage-image.md)

# StorageImage

constructor(filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Creates storage image from the file path with a random ID.

#### Parameters

| | |
|---|---|
| filePath | Image file path. |
<br>
---
<br>

constructor(file: [File](https://developer.android.com/reference/kotlin/java/io/File.html))

Creates storage image from the file with a random ID.

#### Parameters

| | |
|---|---|
| file | Image file. |
<br>
---
<br>

constructor(parcel: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html))

Restores storage image from parcel.

This constructor should be used only by [createFromParcel](-c-r-e-a-t-o-r/create-from-parcel.md) function.

#### Parameters

| | |
|---|---|
| parcel | Parcel that contains storage image. |
<br>
---
<br>

constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Creates storage image with ID and file path.

#### Parameters

| | |
|---|---|
| id | Unique identifier of the image. |
