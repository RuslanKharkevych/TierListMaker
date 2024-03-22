//[app](../../index.md)/[me.khruslan.tierlistmaker.util](index.md)/[getSerializableExtraCompat](get-serializable-extra-compat.md)

# getSerializableExtraCompat

inline fun &lt;[T](get-serializable-extra-compat.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html)&gt; [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html).[getSerializableExtraCompat](get-serializable-extra-compat.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](get-serializable-extra-compat.md)?

A helper to get serializable extra from intent that supports all versions.

Starting from Android 13 uses type-safe overload of the [Intent.getSerializableExtra](https://developer.android.com/reference/kotlin/android/content/Intent.html#getserializableextra) function.

#### Return

The value of the resolved item.

#### Parameters

| | |
|---|---|
| T | Type of the serializable. |
| name | Name of the extra. |
