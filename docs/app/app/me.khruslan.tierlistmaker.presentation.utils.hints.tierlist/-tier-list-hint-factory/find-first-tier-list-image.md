//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintFactory](index.md)/[findFirstTierListImage](find-first-tier-list-image.md)

# findFirstTierListImage

private fun [findFirstTierListImage](find-first-tier-list-image.md)(): [View](https://developer.android.com/reference/kotlin/android/view/View.html)?

Finds the first tier list image.

Loops through tiers from top to bottom (including backlog as a last resort) and checks if it has at least image. If tier is not empty, returns the first image. This function requires layout managers to be attached to recycler views of tiers and images.

#### Return

Image view or null if there are no images.
