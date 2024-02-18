//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[saveImages](save-images.md)

# saveImages

fun [saveImages](save-images.md)(imageUris: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt;)

Saves images to the local storage.

Inserts saved images at the start of the backlog and notifies UI about the updates. Traced with [SaveImagesTrace](../../me.khruslan.tierlistmaker.util.performance/-save-images-trace/index.md).

#### Parameters

| | |
|---|---|
| imageUris | URIs of the images that should be saved. |
