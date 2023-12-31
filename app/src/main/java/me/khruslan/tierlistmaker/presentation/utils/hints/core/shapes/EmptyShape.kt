package me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.animation.LinearInterpolator
import com.takusemba.spotlight.shape.Shape

/**
 * No-op shape. Can be used if target doesn't exist.
 */
class EmptyShape : Shape {
    override val duration = 0L
    override val interpolator = LinearInterpolator()
    override fun draw(canvas: Canvas, point: PointF, value: Float, paint: Paint) {}
}