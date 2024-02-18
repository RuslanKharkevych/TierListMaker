//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag](../index.md)/[ImageDragData](index.md)

# ImageDragData

data class [ImageDragData](index.md)(val image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [DragData](../-drag-data/index.md)

Drag data of the tier list image.

Can be used for both shadow and target inside a tier or backlog.

## Constructors

| | |
|---|---|
| [ImageDragData](-image-drag-data.md) | constructor(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates image drag data from image and its position in tier list. |

## Types

| Name | Summary |
|---|---|
| [Mapper](-mapper/index.md) | object [Mapper](-mapper/index.md)<br>Converts [ClipData](https://developer.android.com/reference/kotlin/android/content/ClipData.html) to [ImageDragData](index.md). |

## Properties

| Name | Summary |
|---|---|
| [image](image.md) | val [image](image.md): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Tier list image. |
| [isBacklogImage](is-backlog-image.md) | val [isBacklogImage](is-backlog-image.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether this drag data represents a backlog image. |
| [itemPosition](item-position.md) | val [itemPosition](item-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the item in tier or backlog. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier or [BACKLOG_TIER_POSITION](../../me.khruslan.tierlistmaker.util/-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md) if image's in the backlog. |

## Functions

| Name | Summary |
|---|---|
| [toClipData](to-clip-data.md) | fun [toClipData](to-clip-data.md)(): [ClipData](https://developer.android.com/reference/kotlin/android/content/ClipData.html)<br>Maps image drag data to the clip data. |
