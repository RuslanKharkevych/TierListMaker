//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[saveTierListToFile](save-tier-list-to-file.md)

# saveTierListToFile

private suspend fun [saveTierListToFile](save-tier-list-to-file.md)(): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?

Generates bitmap from tier list and saves it to a file.

The file name is taken from the tier list title. Updates [loadingProgressLiveData](loading-progress-live-data.md) with indeterminate loading progress.

#### Return

Content URI of the created file or null in case of error.
