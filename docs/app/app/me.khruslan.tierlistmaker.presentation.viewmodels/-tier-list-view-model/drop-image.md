//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[dropImage](drop-image.md)

# dropImage

fun [dropImage](drop-image.md)(dragData: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))

Drops the image into a target.

Pops [DragPocket.target](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/target.md) (or [DragPocket.cachedTarget](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/cached-target.md) if necessary) and notifies the UI that it should be updated with [dragData](drop-image.md). If cached target is not found in the pocket, returns without any action.

#### Parameters

| | |
|---|---|
| dragData | Drag data of the dropped image. |
