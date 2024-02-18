//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[InsertToTier](index.md)

# InsertToTier

data class [InsertToTier](index.md)(val data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)) : [InsertEffect](../-insert-effect/index.md)

Insert effect of the image at given position in tier.

This effect is produced when image is restored or dropped into a tier.

## Constructors

| | |
|---|---|
| [InsertToTier](-insert-to-tier.md) | constructor(data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Creates the effect from tier image drag data. |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | val [data](data.md): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)<br>Image data to insert. |
