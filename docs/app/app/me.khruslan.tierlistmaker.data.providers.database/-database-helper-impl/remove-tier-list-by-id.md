//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelperImpl](index.md)/[removeTierListById](remove-tier-list-by-id.md)

# removeTierListById

open suspend override fun [removeTierListById](remove-tier-list-by-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Removes tier list from the database by identifier.

Due to the limitations of the Paper database, the entire tier list collection must be overwritten. The full algorithm is the following:

1. Read all saved tier lists.

   - If transaction fails - return false.
   - If transaction is successful - move to step 2.
3. Search a tier list by ID.

   - If tier list exists - remove it.
   - If tier list doesn't exist - return false.
5. Save updated tier lists in the database and return the result of this transaction.

#### Return

Whether the tier list was removed successfully.

#### Parameters

| | |
|---|---|
| id | Identifier of the tier list to remove. |
