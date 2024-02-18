//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[logError](log-error.md)

# logError

private fun [logError](log-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Logs generic error.

#### Parameters

| | |
|---|---|
| message | Error message. |

private fun [logError](log-error.md)(transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)

Logs transaction error.

#### Parameters

| | |
|---|---|
| transactionTag | Tag of the transaction. |
| attempt | Transaction attempt. |
| cause | Cause of the transaction failure. |
