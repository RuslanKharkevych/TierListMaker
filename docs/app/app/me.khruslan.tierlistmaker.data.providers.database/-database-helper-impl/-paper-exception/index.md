//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../../index.md)/[DatabaseHelperImpl](../index.md)/[PaperException](index.md)

# PaperException

private class [PaperException](index.md) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)

Exception that can be thrown in case of errors inside the [DatabaseHelperImpl](../index.md).

## Constructors

| | |
|---|---|
| [PaperException](-paper-exception.md) | constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates generic [PaperException](index.md).<br>constructor(transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)<br>Creates [PaperException](index.md) caused by transaction failure. |
