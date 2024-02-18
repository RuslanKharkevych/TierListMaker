//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[updateTierLists](update-tier-lists.md)

# updateTierLists

open suspend override fun [updateTierLists](update-tier-lists.md)(tierLists: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Replaces all tier lists with the new ones.

#### Return

Whether tier lists were updated successfully.

#### Parameters

| | |
|---|---|
| tierLists | Tier lists to save. |

private fun [updateTierLists](update-tier-lists.md)(tierLists: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;, transactionTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Updates all tier lists in the database with the new ones.

Transaction is traced with [UpdateTierListsTrace](../../me.khruslan.tierlistmaker.util.performance/-update-tier-lists-trace/index.md).

#### Return

Whether tier lists was updated successfully.

#### Parameters

| | |
|---|---|
| tierLists | Tier lists to save. |
| transactionTag | Tag used for error logging. |
