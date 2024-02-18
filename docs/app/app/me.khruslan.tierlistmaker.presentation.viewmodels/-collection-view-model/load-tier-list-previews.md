//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[loadTierListPreviews](load-tier-list-previews.md)

# loadTierListPreviews

private fun [loadTierListPreviews](load-tier-list-previews.md)()

Loads [tierLists](tier-lists.md) from [databaseHelper](database-helper.md), maps [tierListPreviews](tier-list-previews.md) and updates [tierListPreviewsLiveData](tier-list-previews-live-data.md).

The function is delayed to make sure that splash screen exit animation finishes gracefully.
