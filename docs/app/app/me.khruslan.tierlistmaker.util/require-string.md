//[app](../../index.md)/[me.khruslan.tierlistmaker.util](index.md)/[requireString](require-string.md)

# requireString

fun [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html).[requireString](require-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Reads string from the parcel.

Can be used instead of [Parcel.readString](https://developer.android.com/reference/kotlin/android/os/Parcel.html#readstring) to ensure that the returned value is non-nullable.

#### Receiver

A parcel that contains string at current data position.

#### Return

Non-nullable string.

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | If unable to resolve a string. |
