//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../../index.md)/[StorageImage](../index.md)/[CREATOR](index.md)

# CREATOR

object [CREATOR](index.md) : [Parcelable.Creator](https://developer.android.com/reference/kotlin/android/os/Parcelable.Creator.html)&lt;[StorageImage](../index.md)&gt; 

Object that generates instances of [StorageImage](../index.md) from [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html).

This object should be used only by Android system.

## Functions

| Name | Summary |
|---|---|
| [createFromParcel](create-from-parcel.md) | open override fun [createFromParcel](create-from-parcel.md)(parcel: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html)): [StorageImage](../index.md)<br>Creates a new instance of the storage image, instantiating it from the given parcel whose data had previously been written by [writeToParcel](../write-to-parcel.md). |
| [newArray](new-array.md) | open override fun [newArray](new-array.md)(size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StorageImage](../index.md)?&gt;<br>Creates a new array of storage images. |
