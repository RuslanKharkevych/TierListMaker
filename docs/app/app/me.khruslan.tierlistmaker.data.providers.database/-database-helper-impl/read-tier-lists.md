//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[readTierLists](read-tier-lists.md)

# readTierLists

private fun [readTierLists](read-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?

Reads all tier lists from the database.

Transaction is traced with [ReadTierListsTrace](../../me.khruslan.tierlistmaker.util.performance/-read-tier-lists-trace/index.md).

#### Return

Fetched tier lists or null if error occurred.
