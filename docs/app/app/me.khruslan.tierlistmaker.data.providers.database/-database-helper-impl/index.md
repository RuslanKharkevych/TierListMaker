//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)

# DatabaseHelperImpl

class [DatabaseHelperImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val defaultTierListCollectionProvider: [DefaultTierListCollectionProvider](../-default-tier-list-collection-provider/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [DatabaseHelper](../-database-helper/index.md)

[DatabaseHelper](../-database-helper/index.md) implementation.

Implemented with [Paper](https://github.com/pilgr/Paper) database. On the first app launch provides the default tier list collection. Moves all transactions to the background thread. Uses retry policy in case transaction fails. Logs all transactions and their results. Collects performance traces for all transactions. This class must be injected as a singleton.

## Constructors

| | |
|---|---|
| [DatabaseHelperImpl](-database-helper-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), defaultTierListCollectionProvider: [DefaultTierListCollectionProvider](../-default-tier-list-collection-provider/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md))<br>Creates a new database helper instance. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Database helper constants for internal usage. |
| [PaperException](-paper-exception/index.md) | private class [PaperException](-paper-exception/index.md) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Exception that can be thrown in case of errors inside the [DatabaseHelperImpl](index.md). |

## Properties

| Name | Summary |
|---|---|
| [defaultTierListCollectionProvided](default-tier-list-collection-provided.md) | private val [defaultTierListCollectionProvided](default-tier-list-collection-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the default tier list collection has already been provided. |
| [defaultTierListCollectionProvider](default-tier-list-collection-provider.md) | private val [defaultTierListCollectionProvider](default-tier-list-collection-provider.md): [DefaultTierListCollectionProvider](../-default-tier-list-collection-provider/index.md)<br>Provides the default tier list collection. |
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides [Dispatchers.IO](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-i-o.html) context. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Traces database transactions. |

## Functions

| Name | Summary |
|---|---|
| [executeTransaction](execute-transaction.md) | private fun &lt;[T](execute-transaction.md)&gt; [executeTransaction](execute-transaction.md)(transaction: () -&gt; [T](execute-transaction.md), onError: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1): [T](execute-transaction.md)?<br>Executes the [transaction](execute-transaction.md) and retries in case of failure. |
| [getTierLists](get-tier-lists.md) | open suspend override fun [getTierLists](get-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?<br>Fetches all saved tier lists. |
| [logError](log-error.md) | private fun [logError](log-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Logs generic error.<br><br>private fun [logError](log-error.md)(transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), attempt: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), cause: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)<br>Logs transaction error. |
| [readTierLists](read-tier-lists.md) | private fun [readTierLists](read-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?<br>Reads all tier lists from the database. |
| [removeTierListById](remove-tier-list-by-id.md) | open suspend override fun [removeTierListById](remove-tier-list-by-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Removes tier list from the database by identifier. |
| [saveTierList](save-tier-list.md) | open suspend override fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Saves tier list in the database. |
| [updateTierLists](update-tier-lists.md) | open suspend override fun [updateTierLists](update-tier-lists.md)(tierLists: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Replaces all tier lists with the new ones.<br><br>private fun [updateTierLists](update-tier-lists.md)(tierLists: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;, transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Updates all tier lists in the database with the new ones. |
