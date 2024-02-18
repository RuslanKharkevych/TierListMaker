package me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.animation.LinearInterpolator
import com.takusemba.spotlight.shape.Shape

/**
 * No-op shape.
 *
 * Can be used if target doesn't exist.
 *
 * @constructor Creates an empty shape.
 */
class EmptyShape : Shape {

    /**
     * Zero duration.
     */
    override val duration = 0L

    /**
     * Linear interpolator.
     */
    override val interpolator = LinearInterpolator()

    /**
     * Draws nothing.
     *
     * @param canvas Canvas to draw on.
     * @param point Coordinate in the center of the anchor.
     * @param value The animated value from 0 to 1.
     * @param paint Paint for customizing ripple style and color.
     */
    override fun draw(canvas: Canvas, point: PointF, value: Float, paint: Paint) {}
}