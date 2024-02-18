//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListImageHolder](index.md)/[onTouch](on-touch.md)

# onTouch

open override fun [onTouch](on-touch.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, event: [MotionEvent](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

If event action is [MotionEvent.ACTION_DOWN](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html#action_down), attempts to start a drag.

Called when a touch event is dispatched to a view.

#### Return

True if touch event was consumed, false otherwise.

#### Parameters

| | |
|---|---|
| view | The view the touch event has been dispatched to. |
| event | The motion event object containing full information about the event. |
