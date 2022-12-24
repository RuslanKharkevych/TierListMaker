package me.khruslan.tierlistmaker.utils.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.TooltipCompat

/**
 * Extended [ImageButton] that shows tooltip on long click. Works on all Android versions. Text of
 * the tooltip is taken from [getContentDescription] function.
 */
class TooltipImageButton : AppCompatImageButton {

    /**
     * @constructor To instantiate [TooltipImageButton] in code.
     * @param context activity context.
     */
    constructor(context: Context) : super(context)

    /**
     * @constructor To instantiate [TooltipImageButton] in XML.
     * @param context activity context.
     * @param attrs attribute values provided via XML.
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * @constructor To instantiate [TooltipImageButton] with default style attributes.
     * @param context activity context.
     * @param attrs attribute values provided via XML.
     * @param defStyleAttrs default style attributes.
     */
    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttrs: Int
    ) : super(context, attrs, defStyleAttrs)

    init {
        TooltipCompat.setTooltipText(this, contentDescription)
    }
}