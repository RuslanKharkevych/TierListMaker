//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[executeTransaction](execute-transaction.md)

# executeTransaction

private fun &lt;[T](execute-transaction.md)&gt; [executeTransaction](execute-transaction.md)(transaction: () -&gt; [T](execute-transaction.md), onError: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1): [T](execute-transaction.md)?

Executes the [transaction](execute-transaction.md) and retries in case of failure.

Each time transaction fails [onError](execute-transaction.md) callback is invoked. Returns null if transaction fails [MAX_TRANSACTION_ATTEMPTS](-constants/-m-a-x_-t-r-a-n-s-a-c-t-i-o-n_-a-t-t-e-m-p-t-s.md) times.

#### Return

Result of the [transaction](execute-transaction.md) or **null** if all attempts failed.

#### Parameters

| | |
|---|---|
| T | Return type of the transaction. |
| transaction | Read/write transaction. |
| onError | Error callback. |
| attempt | Transaction attempt. |
