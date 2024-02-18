//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../../index.md)/[ImageCompressorImpl](../index.md)/[ImageCompressorException](index.md)

# ImageCompressorException

private class [ImageCompressorException](index.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)

Thrown when image compression fails.

Exception message is built from the constructor parameters.

#### Parameters

| | |
|---|---|
| uri | URI of an image that should have been compressed. |
| targetDir | Target directory of the output file. |
| cause | Cause of an error. |

## Constructors

| | |
|---|---|
| [ImageCompressorException](-image-compressor-exception.md) | constructor(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html), targetDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))<br>Creates a new exception instance. |
