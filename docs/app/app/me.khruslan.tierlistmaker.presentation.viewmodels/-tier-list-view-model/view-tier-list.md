//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[viewTierList](view-tier-list.md)

# viewTierList

fun [viewTierList](view-tier-list.md)()

Handles view tier list action.

Attempts to save tier list to a file. On success updates [tierListEvent](tier-list-event.md) with [TierListReadyToView](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-ready-to-view/index.md), on failure - with [TierListExportError](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-export-error/index.md). Logs [TierListViewed](../../me.khruslan.tierlistmaker.util.analytics/-tier-list-viewed/index.md) analytic event.
