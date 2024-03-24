//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[shareTierList](share-tier-list.md)

# shareTierList

fun [shareTierList](share-tier-list.md)()

Handles share tier list action.

Attempts to save tier list to a file. On success updates [tierListEvent](tier-list-event.md) with [TierListReadyToShare](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-ready-to-share/index.md), on failure - with [TierListExportError](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-export-error/index.md). Logs [TierListShared](../../me.khruslan.tierlistmaker.util.analytics/-tier-list-shared/index.md) analytic event.
