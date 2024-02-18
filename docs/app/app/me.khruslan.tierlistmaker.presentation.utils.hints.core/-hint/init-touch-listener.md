//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../index.md)/[Hint](index.md)/[initTouchListener](init-touch-listener.md)

# initTouchListener

private fun [initTouchListener](init-touch-listener.md)(overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md), anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)

Initializes the touch listener of the overlay view.

If user touches the area inside the anchor view, closes the hint group and propagates the touch event. If user touches the area outside of the anchor view, consumes the touch event.

#### Parameters

| | |
|---|---|
| overlay | Overlay view. |
| anchor | Anchor view or null if anchor doesn't exist. |
