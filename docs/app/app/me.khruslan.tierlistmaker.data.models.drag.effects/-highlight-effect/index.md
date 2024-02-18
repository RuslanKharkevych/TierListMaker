//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.drag.effects](../index.md)/[HighlightEffect](index.md)

# HighlightEffect

sealed class [HighlightEffect](index.md) : [DragEffect](../-drag-effect/index.md)

Base class that represents the highlight effect of the current drag location.

Highlight effects are produced when user hovers the drag shadow over views that are able to receive drag events.

#### Inheritors

| |
|---|
| [HighlightInTier](../-highlight-in-tier/index.md) |
| [HighlightInBacklog](../-highlight-in-backlog/index.md) |
| [HighlightLastInTier](../-highlight-last-in-tier/index.md) |
| [HighlightLastInBacklog](../-highlight-last-in-backlog/index.md) |
| [HighlightTrashBin](../-highlight-trash-bin/index.md) |

## Constructors

| | |
|---|---|
| [HighlightEffect](-highlight-effect.md) | protected constructor()<br>Default constructor for use by subclasses. |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | object [Factory](-factory/index.md)<br>Factory for creating highlight effects. |
