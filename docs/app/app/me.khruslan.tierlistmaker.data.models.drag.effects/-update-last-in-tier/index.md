//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[UpdateLastInTier](index.md)

# UpdateLastInTier

data class [UpdateLastInTier](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [UpdateEffect](../-update-effect/index.md)

Update effect of the last image in a tier.

This effect is produced when user drops an image into the drag target that is the last image in a tier.

## Constructors

| | |
|---|---|
| [UpdateLastInTier](-update-last-in-tier.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the effect from image at tier position. |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Image to update. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier. |
