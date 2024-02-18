//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[InsertToBacklog](index.md)

# InsertToBacklog

data class [InsertToBacklog](index.md)(val data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)) : [InsertEffect](../-insert-effect/index.md)

Insert effect of the image at given position in backlog.

This effect is produced when image is restored or dropped into the backlog.

## Constructors

| | |
|---|---|
| [InsertToBacklog](-insert-to-backlog.md) | constructor(data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Creates the effect from backlog image drag data. |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | val [data](data.md): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)<br>Image data to insert. |
