//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag](../index.md)/[TierDragData](index.md)

# TierDragData

data class [TierDragData](index.md)(val tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [DragData](../-drag-data/index.md)

Drag data of the tier target.

In addition to tiers, it can also be used as a drag target of the backlog.

## Constructors

| | |
|---|---|
| [TierDragData](-tier-drag-data.md) | constructor(tierPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates tier drag data from tier position. |

## Properties

| Name | Summary |
|---|---|
| [isBacklog](is-backlog.md) | val [isBacklog](is-backlog.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether this drag data represents a backlog. |
| [tierPosition](tier-position.md) | val [tierPosition](tier-position.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Position of the tier or [BACKLOG_TIER_POSITION](../../me.khruslan.tierlistmaker.util/-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md) in case of the backlog. |
