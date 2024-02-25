//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.drag](../index.md)/[DragPocketImpl](index.md)

# DragPocketImpl

class [DragPocketImpl](index.md) @Inject constructor : [DragPocket](../-drag-pocket/index.md)

[DragPocket](../-drag-pocket/index.md) implementations.

Catches and logs unexpected pocket states.

## Constructors

| | |
|---|---|
| [DragPocketImpl](-drag-pocket-impl.md) | @Inject <br>constructor()<br>Creates a new drag pocket instance. |

## Types

| Name | Summary |
|---|---|
| [DragPocketException](-drag-pocket-exception/index.md) | private class [DragPocketException](-drag-pocket-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Indicates unexpected pocket state. |

## Properties

| Name | Summary |
|---|---|
| [cachedTarget](cached-target.md) | open override var [cachedTarget](cached-target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?<br>The &quot;cached&quot; target is the last non-nullable target. |
| [shadow](shadow.md) | open override var [shadow](shadow.md): [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)?<br>The shadow is the data that is currently dragged. |
| [target](target.md) | open override var [target](target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?<br>The target is the drag location data. |

## Functions

| Name | Summary |
|---|---|
| [logError](log-error.md) | private fun [logError](log-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Logs unexpected pocket states. |
