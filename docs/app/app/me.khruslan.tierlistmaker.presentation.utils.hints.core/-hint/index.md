//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../index.md)/[Hint](index.md)

# Hint

class [Hint](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md), anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, shape: Shape?, effect: Effect?)

Hint is a wrapper on Target.

It includes the highlighted spot as well as the overlay.

#### Parameters

| | |
|---|---|
| name | Name of the hint. |
| overlay | Overlay view of the target. |
| anchor | Anchor view of the target or null if anchor doesn't exist. |
| shape | Shape of the target or null if anchor doesn't exist. |
| effect | Effect of the target or null if anchor doesn't exist. |

## Constructors

| | |
|---|---|
| [Hint](-hint.md) | constructor(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md), anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, shape: Shape?, effect: Effect?)<br>Creates a new hint. |

## Types

| Name | Summary |
|---|---|
| [TargetListener](-target-listener/index.md) | private class [TargetListener](-target-listener/index.md)(val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : OnTargetListener<br>Listener that logs hint started / ended events. |

## Properties

| Name | Summary |
|---|---|
| [onHintListener](on-hint-listener.md) | private var [onHintListener](on-hint-listener.md): [OnHintListener](../-on-hint-listener/index.md)?<br>Listener of hint events. |
| [target](target.md) | val [target](target.md): Target<br>Target of the current hint built from the [Hint](index.md) params. |

## Functions

| Name | Summary |
|---|---|
| [callOnCloseGroup](call-on-close-group.md) | private fun [callOnCloseGroup](call-on-close-group.md)()<br>Invokes [OnHintListener.onCloseGroup](../-on-hint-listener/on-close-group.md) event. |
| [getAnchorPointF](get-anchor-point-f.md) | private fun [getAnchorPointF](get-anchor-point-f.md)(anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?): [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html)<br>Calculates anchor point of the view. |
| [initButtonListeners](init-button-listeners.md) | private fun [initButtonListeners](init-button-listeners.md)(overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md))<br>Initializes listeners of the buttons in the overlay view. |
| [initTouchListener](init-touch-listener.md) | private fun [initTouchListener](init-touch-listener.md)(overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md), anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>Initializes the touch listener of the overlay view. |
| [setOnHintListener](set-on-hint-listener.md) | fun [setOnHintListener](set-on-hint-listener.md)(listener: [OnHintListener](../-on-hint-listener/index.md))<br>Registers [onHintListener](on-hint-listener.md). |
