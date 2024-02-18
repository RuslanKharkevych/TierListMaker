//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[loadingProgressLiveData](loading-progress-live-data.md)

# loadingProgressLiveData

val [loadingProgressLiveData](loading-progress-live-data.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[LoadingProgress](../../me.khruslan.tierlistmaker.presentation.models/-loading-progress/index.md)?&gt;

Live data that notifies observers about the progress of loading image files or creating an image file of the tier list.

If the value is null, then no loading is happening at the moment.
