//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[InsertToEndOfTier](index.md)

# InsertToEndOfTier

data class [InsertToEndOfTier](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [InsertEffect](../-insert-effect/index.md)

Insert effect of the image at the end of the tier.

This effect is produced when image is dropped into the spot in tier where there are no images.

## Constructors

| | |
|---|---|
| [InsertToEndOfTier](-insert-to-end-of-tier.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from image at tier position. |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Image to insert. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier. |
