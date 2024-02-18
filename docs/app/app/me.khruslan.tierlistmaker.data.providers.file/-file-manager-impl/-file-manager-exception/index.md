//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](../../index.md)/[FileManagerImpl](../index.md)/[FileManagerException](index.md)

# FileManagerException

private class [FileManagerException](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)

Thrown on I/O errors caught inside the file manager.

When a system exception is rethrown, make sure to pass it as a cause.

#### Parameters

| | |
|---|---|
| message | Description of the error. |
| cause | Cause of the error. |

## Constructors

| | |
|---|---|
| [FileManagerException](-file-manager-exception.md) | constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null)<br>Creates the exception with message and (optionally) cause. |
