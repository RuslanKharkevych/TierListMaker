//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models.scroll](../index.md)/[AutoScrollState](index.md)

# AutoScrollState

sealed class [AutoScrollState](index.md)

UI state of automatic scrolling.

Indicates whether the automatic scrolling is happening at a given point in time..

#### Inheritors

| |
|---|
| [Idle](-idle/index.md) |
| [Scrolling](-scrolling/index.md) |

## Constructors

| | |
|---|---|
| [AutoScrollState](-auto-scroll-state.md) | protected constructor()<br>Default empty constructor. |

## Types

| Name | Summary |
|---|---|
| [Helpers](-helpers/index.md) | object [Helpers](-helpers/index.md)<br>Convenience functions for creating [AutoScrollState.Scrolling](-scrolling/index.md). |
| [Idle](-idle/index.md) | data object [Idle](-idle/index.md) : [AutoScrollState](index.md)<br>Inactive state. Scrolling is not happening at the moment. |
| [Scrolling](-scrolling/index.md) | data class [Scrolling](-scrolling/index.md)(val direction: [AutoScrollDirection](../-auto-scroll-direction/index.md)) : [AutoScrollState](index.md)<br>Active state. View is being automatically scrolled. |
