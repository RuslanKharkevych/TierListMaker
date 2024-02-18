//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll](../index.md)/[AutoScrollManager](index.md)/[computeScrollState](compute-scroll-state.md)

# computeScrollState

private fun [computeScrollState](compute-scroll-state.md)(dragLocation: [DragLocation](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/index.md)?): [AutoScrollState](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/index.md)

Computes [AutoScrollState](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/index.md) based on drag location.

If [DragLocation.target](../../me.khruslan.tierlistmaker.presentation.models.drag/-drag-location/target.md) is neither [TierDragData](../../me.khruslan.tierlistmaker.data.models.drag/-tier-drag-data/index.md) nor [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md) - returns [AutoScrollState.Idle](../../me.khruslan.tierlistmaker.presentation.models.scroll/-auto-scroll-state/-idle/index.md). Same if target's tier position is [BACKLOG_TIER_POSITION](../../me.khruslan.tierlistmaker.util/-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md). Otherwise delegates to [scrollState](scroll-state.md) to determine whether auto scrolling is needed .

#### Return

Computed auto-scroll state.

#### Parameters

| | |
|---|---|
| dragLocation | The new drag location. |
