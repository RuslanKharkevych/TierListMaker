//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../index.md)/[StorageImage](index.md)/[writeToParcel](write-to-parcel.md)

# writeToParcel

open override fun [writeToParcel](write-to-parcel.md)(dest: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html), flags: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Flatten this storage image into a parcel.

This function should be called only by Android system.

#### Parameters

| | |
|---|---|
| dest | The parcel in which the object should be written. |
| flags | Additional flags about how the object should be written. May be 0 or [Parcelable.PARCELABLE_WRITE_RETURN_VALUE](https://developer.android.com/reference/kotlin/android/os/Parcelable.html#parcelable_write_return_value). |
