//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[handleTierListResult](handle-tier-list-result.md)

# handleTierListResult

fun [handleTierListResult](handle-tier-list-result.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?)

Handles tier list that was either added or updated.

Persists it in the local storage and notifies UI about the updates.

#### Parameters

| | |
|---|---|
| tierList | New or updated tier list. |

#### Throws

| | |
|---|---|
| [TierListResultException](../../me.khruslan.tierlistmaker.presentation.utils.navigation/-tier-list-result-exception/index.md) | If tier list is null. |
