//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[ImageRemoved](index.md)

# ImageRemoved

data class [ImageRemoved](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md) was removed.

This event is sent when an image was dropped into the trash bin as a result of a drag effect.

## Constructors

| | |
|---|---|
| [ImageRemoved](-image-removed.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md))<br>Creates the event from the removed image. |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Image that was removed. |
