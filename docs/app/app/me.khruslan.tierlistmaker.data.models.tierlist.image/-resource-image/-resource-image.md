//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../index.md)/[ResourceImage](index.md)/[ResourceImage](-resource-image.md)

# ResourceImage

constructor(parcel: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html))

Restores resource image from the parcel.

This constructor should be used only by [createFromParcel](-c-r-e-a-t-o-r/create-from-parcel.md) function.

#### Parameters

| | |
|---|---|
| parcel | parcel that contains resource image. |
<br>
---
<br>

constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html) resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Creates resource image with ID and resouce drawable.

#### Parameters

| | |
|---|---|
| id | Unique identifier of the image. |
