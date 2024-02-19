//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[saveTierList](save-tier-list.md)

# saveTierList

open suspend override fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Saves tier list in the database.

Due to the limitations of the Paper database, the entire tier list collection must be overwritten. The full algorithm is the following:

1. Read all saved tier lists.

   - If transaction fails - return false.
   - If transaction is successful - move to step 2.
3. Search a tier list by ID.

   - If tier list exists - replace it with the new one.
   - If tier list doesn't exist - insert the new tier list at the start of the list.
5. Save updated tier lists in the database and return the result of this transaction.

#### Return

Whether the tier list was saved successfully.

#### Parameters

| | |
|---|---|
| tierList | Tier list to save. |
