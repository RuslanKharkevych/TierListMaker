//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[RemoveEffect](index.md)

# RemoveEffect

sealed class [RemoveEffect](index.md) : [DragEffect](../-drag-effect/index.md)

Base class that represents an effect of removing an item from a tier list.

Remove effects are produced when user starts drag or when drag target changes.

#### Inheritors

| |
|---|
| [RemoveFromTier](../-remove-from-tier/index.md) |
| [RemoveFromBacklog](../-remove-from-backlog/index.md) |
| [RemoveLastFromTier](../-remove-last-from-tier/index.md) |
| [RemoveLastFromBacklog](../-remove-last-from-backlog/index.md) |
| [UnhighlightTrashBin](../-unhighlight-trash-bin/index.md) |

## Constructors

| | |
|---|---|
| [RemoveEffect](-remove-effect.md) | protected constructor()<br>Default constructor for use by subclasses. |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | object [Factory](-factory/index.md)<br>Factory for creating remove effects. |
