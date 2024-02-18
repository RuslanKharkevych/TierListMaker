package me.khruslan.tierlistmaker.presentation.utils.hints.core.effects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import androidx.annotation.ColorInt
import com.google.android.material.card.MaterialCardView
import com.takusemba.spotlight.effet.Effect
import com.takusemba.spotlight.effet.RippleEffect
import kotlin.math.min

/**
 * Draws a card ripple effect.
 *
 * An offset of the ripple is equal to the doubled target view padding.
 *
 * @property card Card view of the target.
 * @property color Color of the ripple.
 * @constructor Creates a new card ripple effect with provided color.
 */
class CardRippleEffect(
    private val card: MaterialCardView,
    @ColorInt private val color: Int
) : Effect {

    /**
     * Constants for internal use.
     */
    private companion object Constants {

        /**
         * Maximum alpha value (fully opaque) in [0..255] range.
         *
         * This format for alpha is used in some places in Android SDK, such as [Paint.setAlpha].
         */
        private const val MAX_ALPHA = 255f
    }

    /**
     * Default ripple effect duration.
     */
    override val duration = RippleEffect.DEFAULT_DURATION

    /**
     * Default ripple effect interpolator.
     */
    override val interpolator = RippleEffect.DEFAULT_INTERPOLATOR

    /**
     * Default ripple effect repeat mode.
     */
    override val repeatMode = RippleEffect.DEFAULT_REPEAT_MODE

    /**
     * Draws card ripple effect.
     *
     * The color opacity is animated from 0 to 1. The ripple offset is animated from 0 to the
     * doubled card padding.
     *
     * @param canvas Canvas to draw on.
     * @param point Coordinate in the center of the anchor.
     * @param value The animated value from 0 to 1, which is looped until target finishes.
     * @param paint Paint for customizing ripple style and color.
     */
    override fun draw(canvas: Canvas, point: PointF, value: Float, paint: Paint) {
        var alpha = MAX_ALPHA - MAX_ALPHA * value
        if (color != Color.WHITE) {
            // In dark mode increasing alpha to improve visibility
            alpha = min(alpha * 2, MAX_ALPHA)
        }
        paint.color = color
        paint.alpha = alpha.toInt()

        val cardWidth = card.width - card.paddingLeft - card.paddingRight
        val cardHeight = card.height - card.paddingTop - card.paddingBottom
        val left = point.x - cardWidth / 2f - card.paddingLeft * value * 2
        val top = point.y - cardHeight / 2f - card.paddingTop * value * 2
        val right = point.x + cardWidth / 2f + card.paddingRight * value * 2
        val bottom = point.y + cardHeight / 2f + card.paddingBottom * value * 2
        val radius = card.radius

        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint)
    }
}