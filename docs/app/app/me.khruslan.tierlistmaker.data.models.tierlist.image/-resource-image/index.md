//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../index.md)/[ResourceImage](index.md)

# ResourceImage

class [ResourceImage](index.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)val resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [Image](../-image/index.md), [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Image stored as a [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html) resource.

Used for highlighting drag targets or when image can't be saved to a file due to an error.

#### Parameters

| | |
|---|---|
| id | Unique identifier of the image. |

## Constructors

| | |
|---|---|
| [ResourceImage](-resource-image.md) | constructor(parcel: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html))<br>Restores resource image from the parcel.<br><br>constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates resource image with ID and resouce drawable. |

## Types

| Name | Summary |
|---|---|
| [CREATOR](-c-r-e-a-t-o-r/index.md) | object [CREATOR](-c-r-e-a-t-o-r/index.md) : [Parcelable.Creator](https://developer.android.com/reference/kotlin/android/os/Parcelable.Creator.html)&lt;[ResourceImage](index.md)&gt; <br>Object that generates instances of [ResourceImage](index.md) from [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html). |

## Properties

| Name | Summary |
|---|---|
| [resId](res-id.md) | val [resId](res-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Drawable resource id. |

## Functions

| Name | Summary |
|---|---|
| [describeContents](describe-contents.md) | open override fun [describeContents](describe-contents.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Describes the kinds of special objects contained in the resource image instance's marshaled representation. |
| [equals](equals.md) | open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether some other object is equal to this one. |
| [hashCode](hash-code.md) | open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns a hash code value for this resource image. |
| [toString](to-string.md) | open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a string representation of this resource image. |
| [writeToParcel](write-to-parcel.md) | open override fun [writeToParcel](write-to-parcel.md)(dest: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html), flags: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Flatten this resource image into a parcel. |
