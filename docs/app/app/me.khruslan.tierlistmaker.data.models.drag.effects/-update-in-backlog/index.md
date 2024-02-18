//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[UpdateInBacklog](index.md)

# UpdateInBacklog

data class [UpdateInBacklog](index.md)(val data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)) : [UpdateEffect](../-update-effect/index.md)

Update effect of the image in the backlog.

This effect is produced when user drops an image into the drag target that is a backlog image.

## Constructors

| | |
|---|---|
| [UpdateInBacklog](-update-in-backlog.md) | constructor(data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Creates the effect from backlog image drag data. |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | val [data](data.md): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)<br>Image data to update. |
