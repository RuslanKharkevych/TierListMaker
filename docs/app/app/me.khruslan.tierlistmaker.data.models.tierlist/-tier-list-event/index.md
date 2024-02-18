//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierListEvent](index.md)

# TierListEvent

sealed class [TierListEvent](index.md)

Base class that represents the event that happened in a [TierList](../-tier-list/index.md).

Often, but not exclusively, it is a result of a [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md). All tier list events are immutable.

#### Inheritors

| |
|---|
| [BacklogDataChanged](../-backlog-data-changed/index.md) |
| [BacklogImagesAdded](../-backlog-images-added/index.md) |
| [BacklogItemInserted](../-backlog-item-inserted/index.md) |
| [TierListChanged](../-tier-list-changed/index.md) |
| [TierChanged](../-tier-changed/index.md) |
| [TierInserted](../-tier-inserted/index.md) |
| [ImageSizeUpdated](../-image-size-updated/index.md) |
| [TrashBinHighlightUpdated](../-trash-bin-highlight-updated/index.md) |
| [ImageRemoved](../-image-removed/index.md) |
| [TierListReadyToShare](../-tier-list-ready-to-share/index.md) |
| [TierListReadyToView](../-tier-list-ready-to-view/index.md) |
| [TierListExportError](../-tier-list-export-error/index.md) |

## Constructors

| | |
|---|---|
| [TierListEvent](-tier-list-event.md) | protected constructor()<br>Default constructor for use by subclasses. |
