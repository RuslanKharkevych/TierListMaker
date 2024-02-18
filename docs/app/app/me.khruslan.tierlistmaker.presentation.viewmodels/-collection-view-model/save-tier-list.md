//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[saveTierList](save-tier-list.md)

# saveTierList

private fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))

Asynchronously saves added or updated tier list in the local storage.

Triggers [errorEvent](error-event.md) in case of error. Note that in such case [tierLists](tier-lists.md) will become out of sync with the real data.

#### Parameters

| | |
|---|---|
| tierList | New or updated tier list. |
