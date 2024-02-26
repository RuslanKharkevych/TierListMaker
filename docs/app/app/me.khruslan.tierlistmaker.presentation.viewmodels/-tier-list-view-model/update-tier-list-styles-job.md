//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[updateTierListStylesJob](update-tier-list-styles-job.md)

# updateTierListStylesJob

private var [updateTierListStylesJob](update-tier-list-styles-job.md): [Job](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)?

Updates styles of all tiers in the tier list.

Make sure to cancel the old job whenever the new one is launched to avoid race condition bugs.
