//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelper](index.md)/[removeTierListById](remove-tier-list-by-id.md)

# removeTierListById

abstract suspend fun [removeTierListById](remove-tier-list-by-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Removes tier list from the database by identifier.

In case tier list with the provided identifier doesn't exist the transaction will fail.

#### Return

Whether the tier list was removed successfully.

#### Parameters

| | |
|---|---|
| id | Identifier of the tier list to remove. |
