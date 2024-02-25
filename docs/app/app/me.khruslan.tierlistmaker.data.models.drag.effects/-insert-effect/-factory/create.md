//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../../index.md)/[InsertEffect](../index.md)/[Factory](index.md)/[create](create.md)

# create

fun [create](create.md)(data: [ImageDragData](../../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)): [InsertEffect](../index.md)

Creates insert effect from the image drag data.

Used for creating effects as a result of restoring the image in the tier list.

#### Return

Created insert effect.

#### Parameters

| | |
|---|---|
| data | Image data to restore. |
<br>
---
<br>

fun [create](create.md)(shadow: [ImageDragData](../../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md), target: [DragData](../../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)): [InsertEffect](../index.md)

Creates insert effect from shadow and target.

Used for creating effects as a result of inserting the image into specific target. Note that it's impossible to insert into the trash bin because [ThrowToTrashBin](../../-throw-to-trash-bin/index.md) is an update effect.

#### Return

Created insert effect.

#### Parameters

| | |
|---|---|
| shadow | Image data to insert. |
| target | Data of the target. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | If target is [TrashBinDragData](../../../me.khruslan.tierlistmaker.data.models.drag/-trash-bin-drag-data/index.md). |
