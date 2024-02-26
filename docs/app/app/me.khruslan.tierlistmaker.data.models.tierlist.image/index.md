//[app](../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](index.md)

# Package-level declarations

Data models of tier list images.

## Types

| Name | Summary |
|---|---|
| [Image](-image/index.md) | sealed class [Image](-image/index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)<br>Base class that represents the image in the [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md). |
| [ResourceImage](-resource-image/index.md) | class [ResourceImage](-resource-image/index.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html) val resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [Image](-image/index.md), [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)<br>Image stored as a [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html) resource. |
| [StorageImage](-storage-image/index.md) | class [StorageImage](-storage-image/index.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Image](-image/index.md), [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)<br>Image stored as a file. |
