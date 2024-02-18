//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[UpdateLastInBacklog](index.md)

# UpdateLastInBacklog

data class [UpdateLastInBacklog](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)) : [UpdateEffect](../-update-effect/index.md)

Update effect of the last image in the backlog.

This effect is produced when user drops an image into the drag target that is the last image in backlog.

## Constructors

| | |
|---|---|
| [UpdateLastInBacklog](-update-last-in-backlog.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md))<br>Creates the effect from backlog image. |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Image to update. |
