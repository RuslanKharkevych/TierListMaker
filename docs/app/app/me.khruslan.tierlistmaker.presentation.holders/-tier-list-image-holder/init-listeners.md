//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListImageHolder](index.md)/[initListeners](init-listeners.md)

# initListeners

private fun [initListeners](init-listeners.md)(dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html))

Initializes image view listeners.

1. Touch events are handled by [onTouch](on-touch.md) function.
2. Drag events are propagated to [dragListener](init-listeners.md).

#### Parameters

| | |
|---|---|
| dragListener | Listener of tier list drag events. |
