//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[InsertToEndOfBacklog](index.md)

# InsertToEndOfBacklog

data class [InsertToEndOfBacklog](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)) : [InsertEffect](../-insert-effect/index.md)

Insert effect of the image at the end of the backlog.

This effect is produced when image is dropped into the spot in backlog where there are no images.

## Constructors

| | |
|---|---|
| [InsertToEndOfBacklog](-insert-to-end-of-backlog.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md))<br>Creates the effect from backlog image. |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Image to insert. |
