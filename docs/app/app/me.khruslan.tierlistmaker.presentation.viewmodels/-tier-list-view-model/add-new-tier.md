//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[addNewTier](add-new-tier.md)

# addNewTier

fun [addNewTier](add-new-tier.md)()

Inserts a new empty tier at the end.

Updates [tierListEvent](tier-list-event.md) with [TierInserted](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-inserted/index.md). Cancels current [updateTierListStylesJob](update-tier-list-styles-job.md) and starts a new job to update style of all tiers.
