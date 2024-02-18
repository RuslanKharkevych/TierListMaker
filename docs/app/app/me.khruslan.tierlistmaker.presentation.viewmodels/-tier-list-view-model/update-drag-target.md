//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[updateDragTarget](update-drag-target.md)

# updateDragTarget

fun [updateDragTarget](update-drag-target.md)(newTarget: [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?)

Updates the drag target.

- Pops old target from [dragPocket](drag-pocket.md).
- Saves [newTarget](update-drag-target.md) in the [dragPocket](drag-pocket.md).
- Notifies UI about the updates.

#### Parameters

| | |
|---|---|
| newTarget | Drag data of the new target or null if target was removed. |
