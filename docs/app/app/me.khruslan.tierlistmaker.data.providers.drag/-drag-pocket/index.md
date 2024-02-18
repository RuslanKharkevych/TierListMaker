//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.drag](../index.md)/[DragPocket](index.md)

# DragPocket

interface [DragPocket](index.md)

A &quot;pocket&quot; for the drag data that needs to be temporarily kept in memory.

The concrete implementation of this interface might apply additional preconditions and postconditions.

#### Inheritors

| |
|---|
| [DragPocketImpl](../-drag-pocket-impl/index.md) |

## Properties

| Name | Summary |
|---|---|
| [cachedTarget](cached-target.md) | abstract val [cachedTarget](cached-target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?<br>The &quot;cached&quot; target is the last non-nullable [target](target.md). |
| [shadow](shadow.md) | abstract var [shadow](shadow.md): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)?<br>The shadow is the data that is currently dragged. |
| [target](target.md) | abstract var [target](target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?<br>The target is the drag location data. |
