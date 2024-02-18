//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[AnimatorUtils](index.md)

# AnimatorUtils

object [AnimatorUtils](index.md)

Set of utilities to simplify creation of complex [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html) objects.

Built on top of [ViewAnimationUtils](https://developer.android.com/reference/kotlin/android/view/ViewAnimationUtils.html).

## Functions

| Name | Summary |
|---|---|
| [addOnEndAction](add-on-end-action.md) | inline fun [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html).[addOnEndAction](add-on-end-action.md)(crossinline action: ([Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html)<br>Adds an action that will be invoked when the animation has ended. |
| [createCircularConceal](create-circular-conceal.md) | fun [createCircularConceal](create-circular-conceal.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)): [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html)<br>Animates a clipping circle to hide a view. |
