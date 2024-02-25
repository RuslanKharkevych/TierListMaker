//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../../index.md)/[DatabaseHelperImpl](../index.md)/[PaperException](index.md)/[PaperException](-paper-exception.md)

# PaperException

constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Creates generic [PaperException](index.md).

#### Parameters

| | |
|---|---|
| message | Error message. |
<br>
---
<br>

constructor(transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)

Creates [PaperException](index.md) caused by transaction failure.

#### Parameters

| | |
|---|---|
| transactionTag | Tag of the transaction. |
| attempt | Transaction attempt. |
| cause | Cause of the transaction failure. |
