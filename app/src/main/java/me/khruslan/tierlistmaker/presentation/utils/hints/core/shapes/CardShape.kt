package me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes

import com.google.android.material.card.MaterialCardView
import com.takusemba.spotlight.shape.RoundedRectangle
import com.takusemba.spotlight.shape.Shape

/**
 * Shape of a card.
 *
 * @param delegate rounded rectangle shape delegate.
 */
class CardShape private constructor(delegate: RoundedRectangle) : Shape by delegate {

    /**
     * @constructor Creates card shape from the card view.
     * @param card card view of the target.
     */
    constructor(card: MaterialCardView) : this(
        RoundedRectangle(
            height = card.height.toFloat() - card.paddingTop - card.paddingBottom,
            width = card.width.toFloat() - card.paddingLeft - card.paddingRight,
            radius = card.radius
        )
    )
}