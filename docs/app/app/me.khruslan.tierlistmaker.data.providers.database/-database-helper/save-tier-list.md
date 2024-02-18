//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DatabaseHelper](index.md)/[saveTierList](save-tier-list.md)

# saveTierList

abstract suspend fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Saves the tier list in the database.

If a tier list with the same ID already exists in the database, replaces the existing one. Otherwise a new tier list is added.

#### Return

Whether the tier list was saved successfully.

#### Parameters

| | |
|---|---|
| tierList | tier list to save. |
