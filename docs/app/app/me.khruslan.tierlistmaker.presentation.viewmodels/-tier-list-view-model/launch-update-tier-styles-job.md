//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[launchUpdateTierStylesJob](launch-update-tier-styles-job.md)

# launchUpdateTierStylesJob

private fun [launchUpdateTierStylesJob](launch-update-tier-styles-job.md)(): Job

Creates and starts a job that updates styles of all tiers.

On job completion updates [tierListEvent](tier-list-event.md) with [TierListChanged](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-changed/index.md).

#### Return

Created job.
