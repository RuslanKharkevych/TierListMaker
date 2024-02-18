//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[ImageSizeUpdated](index.md)

# ImageSizeUpdated

data class [ImageSizeUpdated](index.md)(val imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md) size was updated.

This event is sent when [TierList.zoomValue](../-tier-list/zoom-value.md) changed as a result of user's action.

## Constructors

| | |
|---|---|
| [ImageSizeUpdated](-image-size-updated.md) | constructor(imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the event from image size. |

## Properties

| Name | Summary |
|---|---|
| [imageSize](image-size.md) | val [imageSize](image-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Size of the tier list image. |
