//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[addOrUpdateTierList](add-or-update-tier-list.md)

# addOrUpdateTierList

private fun [addOrUpdateTierList](add-or-update-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))

Asynchronously updates tier lists with added or updated tier list.

If tier list is found by ID:

- Updates the tier list in [tierLists](tier-lists.md).
- Updates the respective preview in [tierListPreviews](tier-list-previews.md).
- Triggers [updatePreviewEvent](update-preview-event.md) update.

If tier list is not found by ID:

- Inserts the tier list into [tierLists](tier-lists.md).
- Inserts the respective preview into [tierListPreviews](tier-list-previews.md).
- Updates [listStateLiveData](list-state-live-data.md) with [ListState.Filled](../../me.khruslan.tierlistmaker.presentation.models/-list-state/-filled/index.md).
- Triggers [updatePreviewEvent](update-preview-event.md) update.

#### Parameters

| | |
|---|---|
| tierList | New or updated tier list. |
