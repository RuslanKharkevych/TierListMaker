//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models.drag](../index.md)/[TargetLocation](index.md)

# TargetLocation

data class [TargetLocation](index.md)(val adapterPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val verticalOffset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Model that contains specific information about the drag target.

Suitable only for targets of [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md) and [TierDragData](../../me.khruslan.tierlistmaker.data.models.drag/-tier-drag-data/index.md) types. Is useful for determining position of the drag cursor on the y-axis withing the tier list.

#### See also

| |
|---|
| [DragLocation](../-drag-location/index.md) |

## Constructors

| | |
|---|---|
| [TargetLocation](-target-location.md) | constructor(adapterPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), verticalOffset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates a new target location instance. |

## Properties

| Name | Summary |
|---|---|
| [adapterPosition](adapter-position.md) | val [adapterPosition](adapter-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Adapter position of the tier where the target is located. |
| [verticalOffset](vertical-offset.md) | val [verticalOffset](vertical-offset.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Vertical offset of the cursor relative to the target. |
