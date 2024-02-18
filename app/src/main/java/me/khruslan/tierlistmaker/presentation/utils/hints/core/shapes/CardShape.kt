package me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes

import com.google.android.material.card.MaterialCardView
import com.takusemba.spotlight.shape.RoundedRectangle
import com.takusemba.spotlight.shape.Shape

/**
 * Shape of a card.
 *
 * It's a helper for creating [RoundedRectangle] shape for the [MaterialCardView].
 *
 * @param delegate Rounded rectangle shape delegate.
 * @constructor Creates a card shape from the delegate.
 */
class CardShape private constructor(delegate: RoundedRectangle) : Shape by delegate {

    /**
     * Creates card shape from the card view.
     *
     * @param card Card view of the target.
     */
    constructor(card: MaterialCardView) : this(
        RoundedRectangle(
            height = card.height.toFloat() - card.paddingTop - card.paddingBottom,
            width = card.width.toFloat() - card.paddingLeft - card.paddingRight,
            radius = card.radius
        )
    )
}