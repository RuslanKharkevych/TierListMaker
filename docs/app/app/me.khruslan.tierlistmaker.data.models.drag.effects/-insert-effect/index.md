//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[InsertEffect](index.md)

# InsertEffect

sealed class [InsertEffect](index.md) : [DragEffect](../-drag-effect/index.md)

Base class that represents the effect of inserting the image data into a tier list.

Insert effects are usually produced when drag images are restored after drag has ended. Besides that, they can also be produced when drag images are dropped into the target. This could happen if the drop is too fast for the highlight effect to take place.

#### Inheritors

| |
|---|
| [InsertToBacklog](../-insert-to-backlog/index.md) |
| [InsertToTier](../-insert-to-tier/index.md) |
| [InsertToEndOfBacklog](../-insert-to-end-of-backlog/index.md) |
| [InsertToEndOfTier](../-insert-to-end-of-tier/index.md) |

## Constructors

| | |
|---|---|
| [InsertEffect](-insert-effect.md) | protected constructor()<br>Default constructor for use by subclasses. |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | object [Factory](-factory/index.md)<br>Factory for creating insert effects. |
