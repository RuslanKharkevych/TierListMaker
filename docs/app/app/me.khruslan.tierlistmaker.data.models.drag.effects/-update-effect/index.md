//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[UpdateEffect](index.md)

# UpdateEffect

sealed class [UpdateEffect](index.md) : [DragEffect](../-drag-effect/index.md)

Base class that represents the effect of updating an item in a tier list.

Update effects are produced when an image is dropped into a drag target.

#### Inheritors

| |
|---|
| [UpdateInBacklog](../-update-in-backlog/index.md) |
| [UpdateInTier](../-update-in-tier/index.md) |
| [UpdateLastInBacklog](../-update-last-in-backlog/index.md) |
| [UpdateLastInTier](../-update-last-in-tier/index.md) |
| [ThrowToTrashBin](../-throw-to-trash-bin/index.md) |

## Constructors

| | |
|---|---|
| [UpdateEffect](-update-effect.md) | protected constructor()<br>Default constructor for use by subclasses. |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | object [Factory](-factory/index.md)<br>Factory for creating update effects. |
