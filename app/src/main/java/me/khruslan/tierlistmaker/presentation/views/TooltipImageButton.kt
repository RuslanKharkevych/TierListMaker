package me.khruslan.tierlistmaker.presentation.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.TooltipCompat

/**
 * Extended image button that shows tooltip on long click.
 *
 * Works on all Android versions. Text of the tooltip is taken from
 * [AppCompatImageButton.getContentDescription] function.
 */
class TooltipImageButton : AppCompatImageButton {

    /**
     * Instantiates tooltip image button from code.
     *
     * @param context Activity context.
     */
    constructor(context: Context) : super(context)

    /**
     * Instantiates tooltip image button from XML.
     *
     * @param context Activity context.
     * @param attrs Attribute values provided via XML.
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * Instantiates tooltip image button from XML with default style attributes.
     *
     * @param context Activity context.
     * @param attrs Attribute values provided via XML.
     * @param defStyleAttrs Default style attributes.
     */
    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttrs: Int
    ) : super(context, attrs, defStyleAttrs)

    init {
        invalidateTooltipText()
    }

    /**
     * Updates button's content description and triggers [invalidateTooltipText].
     *
     * @param contentDescription The content description to set.
     */
    override fun setContentDescription(contentDescription: CharSequence?) {
        super.setContentDescription(contentDescription)
        invalidateTooltipText()
    }

    /**
     * Synchronizes tooltip text with content description.
     **/
    private fun invalidateTooltipText() {
        TooltipCompat.setTooltipText(this, contentDescription)
    }
}