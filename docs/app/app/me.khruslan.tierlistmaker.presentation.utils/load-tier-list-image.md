//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[loadTierListImage](load-tier-list-image.md)

# loadTierListImage

fun [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html).[loadTierListImage](load-tier-list-image.md)(filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Loads the image from the device file system into the [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html) in the tier list.

The image is loaded with [CrossfadeTransition](-crossfade-transition.md) and cropped to fit the view size. No placeholder is shown during loading, only in case loading has failed. No data is saved to a disk cache, only memory cache is used.

#### Receiver

Image view to load into.

#### Parameters

| | |
|---|---|
| filePath | Full path to the file in device storage or assets. |
