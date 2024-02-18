//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelper](index.md)/[getTierLists](get-tier-lists.md)

# getTierLists

abstract suspend fun [getTierLists](get-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?

Fetches all saved tier lists.

Can return empty list if no tier lists are found.

#### Return

Fetched tier lists or null if error occurred.
