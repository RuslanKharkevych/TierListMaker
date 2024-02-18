//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[updateTierLists](update-tier-lists.md)

# updateTierLists

private fun [updateTierLists](update-tier-lists.md)()

Asynchronously updates tier lists in the local storage.

Triggers [errorEvent](error-event.md) on database transaction failure. Note that in such case [tierLists](tier-lists.md) will become out of sync with the real data.
