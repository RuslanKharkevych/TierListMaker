//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[AnimatorUtils](index.md)/[addOnEndAction](add-on-end-action.md)

# addOnEndAction

inline fun [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html).[addOnEndAction](add-on-end-action.md)(crossinline action: ([Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html)

Adds an action that will be invoked when the animation has ended.

Same as [doOnEnd](https://developer.android.com/reference/kotlin/androidx/core/animation/package-summary.html) but returns [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html) instead of [Animator.AnimatorListener](https://developer.android.com/reference/kotlin/android/animation/Animator.AnimatorListener.html) to allow using it in a builder.

#### Receiver

Animator instance to listen to.

#### Return

Same animator instance that was used as extension receiver.

#### Parameters

| | |
|---|---|
| action | Action that should be invoked. |
