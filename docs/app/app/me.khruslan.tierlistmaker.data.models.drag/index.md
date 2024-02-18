//[app](../../index.md)/[me.khruslan.tierlistmaker.data.models.drag](index.md)

# Package-level declarations

Data models of drag entities. Contains information about draggable items.

## Types

| Name | Summary |
|---|---|
| [DragData](-drag-data/index.md) | sealed class [DragData](-drag-data/index.md)<br>Base class that contains the data of various drag items. |
| [ImageDragData](-image-drag-data/index.md) | data class [ImageDragData](-image-drag-data/index.md)(val image: [Image](../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), val itemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [DragData](-drag-data/index.md)<br>Drag data of the tier list image. |
| [TierDragData](-tier-drag-data/index.md) | data class [TierDragData](-tier-drag-data/index.md)(val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [DragData](-drag-data/index.md)<br>Drag data of the tier target. |
| [TrashBinDragData](-trash-bin-drag-data/index.md) | data object [TrashBinDragData](-trash-bin-drag-data/index.md) : [DragData](-drag-data/index.md)<br>Drag data of the trash bin. |
