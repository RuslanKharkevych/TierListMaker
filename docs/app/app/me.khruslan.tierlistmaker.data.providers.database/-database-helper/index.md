//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelper](index.md)

# DatabaseHelper

interface [DatabaseHelper](index.md)

Local storage provider.

Used for persisting tier lists. All functions are asynchronous. The subclasses must ensure that all functions can be safely called from the main thread. All exceptions must be handled internally. The result of the transaction is returned as a nullable type for read operations and a boolean flag for write operations.

#### Inheritors

| |
|---|
| [DatabaseHelperImpl](../-database-helper-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [getTierLists](get-tier-lists.md) | abstract suspend fun [getTierLists](get-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?<br>Fetches all saved tier lists. |
| [removeTierListById](remove-tier-list-by-id.md) | abstract suspend fun [removeTierListById](remove-tier-list-by-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Removes tier list from the database by identifier. |
| [saveTierList](save-tier-list.md) | abstract suspend fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Saves the tier list in the database. |
| [updateTierLists](update-tier-lists.md) | abstract suspend fun [updateTierLists](update-tier-lists.md)(tierLists: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Replaces all tier lists with the new ones. |
