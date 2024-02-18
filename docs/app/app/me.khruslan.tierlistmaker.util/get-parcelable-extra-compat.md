//[app](../../index.md)/[me.khruslan.tierlistmaker.util](index.md)/[getParcelableExtraCompat](get-parcelable-extra-compat.md)

# getParcelableExtraCompat

inline fun &lt;[T](get-parcelable-extra-compat.md) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)&gt; [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html).[getParcelableExtraCompat](get-parcelable-extra-compat.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](get-parcelable-extra-compat.md)?

A helper to get parcelable extra from intent that supports all versions.

Starting from Android 13 uses type-safe overload of the [Intent.getParcelableExtra](https://developer.android.com/reference/kotlin/android/content/Intent.html#getparcelableextra) function.

#### Return

The value of the resolved item.

#### Parameters

| | |
|---|---|
| T | Type of the parcelable. |
| name | Name of the extra. |
