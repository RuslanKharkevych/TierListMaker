//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[getTierLists](get-tier-lists.md)

# getTierLists

open suspend override fun [getTierLists](get-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;?

Fetches all saved tier lists.

If it's the first application launch, returns the default tier list collection. The full algorithm is the following:

1. Read all tier lists from the database.

   - If transaction fails, or it's successful and tier lists are not empty, or the default     tier list collection has been already provided - return the result.
   - If tier lists are empty and the default tier list collection wasn't provided before -     move to step 2.
3. Provide the default tier list collection and save it in the database.
4. Read all tier lists from the database again and return the result.

#### Return

Fetched tier lists or null if error occurred.
