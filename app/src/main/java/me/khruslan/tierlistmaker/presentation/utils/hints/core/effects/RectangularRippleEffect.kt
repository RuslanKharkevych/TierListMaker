package me.khruslan.tierlistmaker.presentation.utils.hints.core.effects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import androidx.annotation.ColorInt

/**
 * Draws a rectangular ripple effect.
 *
 * The corners of the rectangle are sharp.
 *
 * @property height Height of the rectangle in pixels.
 * @property width Width of the rectangle in pixels.
 * @property color Color of the ripple.
 * @constructor Creates a new square ripple effect with provided attributes.
 */
class RectangularRippleEffect(
    private val height: Int,
    private val width: Int,
    private val offset: Int,
    @ColorInt private val color: Int
) : BaseRectangularRippleEffect() {

    /**
     * Draws a square ripple effect.
     *
     * The color opacity is animated from 0 to 1. The ripple offset is animated from 0 to [offset].
     *
     * @param canvas Canvas to draw on.
     * @param point Coordinate in the center of the anchor.
     * @param value The animated value from 0 to 1, which is looped until target finishes.
     * @param paint Paint for customizing ripple style and color.
     */
    override fun draw(canvas: Canvas, point: PointF, value: Float, paint: Paint) {
        val alpha = MAX_ALPHA - MAX_ALPHA * value
        paint.color = color
        paint.alpha = alpha.toInt()

        val left = point.x - width / 2f - offset
        val top = point.y - height / 2f - offset
        val right = point.x + width / 2f + offset
        val bottom = point.y + height / 2f + offset
        val radius = 0f

        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint)
    }
}