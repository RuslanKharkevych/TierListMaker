//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.views](../index.md)/[TooltipImageButton](index.md)

# TooltipImageButton

class [TooltipImageButton](index.md) : [AppCompatImageButton](https://developer.android.com/reference/kotlin/androidx/appcompat/widget/AppCompatImageButton.html)

Extended image button that shows tooltip on long click.

Works on all Android versions. Text of the tooltip is taken from [getContentDescription](../../../../app/me.khruslan.tierlistmaker.presentation.views/-tooltip-image-button/get-content-description.md) function.

## Constructors

| | |
|---|---|
| [TooltipImageButton](-tooltip-image-button.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Instantiates tooltip image button from code.<br><br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html))<br>Instantiates tooltip image button from XML.<br><br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html), defStyleAttrs: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Instantiates tooltip image button from XML with default style attributes. |

## Functions

| Name | Summary |
|---|---|
| [invalidateTooltipText](invalidate-tooltip-text.md) | private fun [invalidateTooltipText](invalidate-tooltip-text.md)()<br>Synchronizes tooltip text with content description. |
| [setContentDescription](set-content-description.md) | open override fun [setContentDescription](set-content-description.md)(contentDescription: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?)<br>Updates button's content description and triggers [invalidateTooltipText](invalidate-tooltip-text.md). |
