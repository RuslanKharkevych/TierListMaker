//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.views](../index.md)/[HintOverlayView](index.md)

# HintOverlayView

class [HintOverlayView](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [ConstraintLayout](https://developer.android.com/reference/kotlin/androidx/constraintlayout/widget/ConstraintLayout.html)

View of the [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md) overlay.

Includes hint text and buttons: &quot;Previous&quot;, &quot;Next&quot; and &quot;Close&quot;. This view doesn't support XML attributes. It must be instantiated only in Java/Kotlin code.

#### Parameters

| | |
|---|---|
| context | Activity context. |

## Constructors

| | |
|---|---|
| [HintOverlayView](-hint-overlay-view.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Creates the hint overlay view with empty hint text and enabled previous/next buttons. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): ViewHintOverlayBinding<br>Binding of the hint overlay view. |

## Functions

| Name | Summary |
|---|---|
| [disableNextButton](disable-next-button.md) | fun [disableNextButton](disable-next-button.md)()<br>Disables &quot;Next&quot; button. |
| [disablePreviousButton](disable-previous-button.md) | fun [disablePreviousButton](disable-previous-button.md)()<br>Disables &quot;Previous&quot; button. |
| [setHintText](set-hint-text.md) | fun [setHintText](set-hint-text.md)(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Updates the hint text. |
| [setOnCloseButtonClickListener](set-on-close-button-click-listener.md) | fun [setOnCloseButtonClickListener](set-on-close-button-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Registers click listener for the &quot;Close&quot; button. |
| [setOnNextButtonClickListener](set-on-next-button-click-listener.md) | fun [setOnNextButtonClickListener](set-on-next-button-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Registers click listener for the &quot;Next&quot; button. |
| [setOnPreviousButtonClickListener](set-on-previous-button-click-listener.md) | fun [setOnPreviousButtonClickListener](set-on-previous-button-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Registers click listener for the &quot;Previous&quot; button. |
