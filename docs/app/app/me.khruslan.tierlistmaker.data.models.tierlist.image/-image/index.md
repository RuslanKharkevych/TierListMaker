//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../index.md)/[Image](index.md)

# Image

sealed class [Image](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Base class that represents the image in the [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md).

Image can be stored as files or drawable resources. This class has immutable contract. Its subclasses must implement [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html) interface. They can be stored in the database or passed as navigation arguments.

#### Inheritors

| |
|---|
| [ResourceImage](../-resource-image/index.md) |
| [StorageImage](../-storage-image/index.md) |

## Constructors

| | |
|---|---|
| [Image](-image.md) | protected constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Default constructor for use by subclasses. |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | object [Factory](-factory/index.md)<br>Factory for creating images from payload. |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique identifier of the image. |
| [payload](payload.md) | val [payload](payload.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Either file path or drawable id. Used to convert image to the clip data. |
