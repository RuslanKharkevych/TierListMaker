package me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes

import com.takusemba.spotlight.shape.RoundedRectangle
import com.takusemba.spotlight.shape.Shape

/**
 * Shape of a square.
 *
 * It's a helper for creating [RoundedRectangle] shape with two adjacent equal sides and sharp
 * corners.
 *
 * @param delegate Rounded rectangle shape delegate.
 * @constructor Creates a square shape from the delegate.
 */
class SquareShape private constructor(delegate: RoundedRectangle) : Shape by delegate {

    /**
     * Creates square shape with given size.
     *
     * @param size Length of the square side in pixels.
     */
    constructor(size: Int) : this(
        RoundedRectangle(
            height = size.toFloat(),
            width = size.toFloat(),
            radius = 0f
        )
    )
}