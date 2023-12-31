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
 * Draws a card ripple effect. An offset of the ripple is equal to the doubled target view padding.
 *
 * @property card card view of the target.
 * @property color color of the ripple.
 */
class CardRippleEffect(
    private val card: MaterialCardView,
    @ColorInt private val color: Int
) : Effect {

    private companion object {
        private const val MAX_ALPHA = 255f
    }

    override val duration = RippleEffect.DEFAULT_DURATION
    override val interpolator = RippleEffect.DEFAULT_INTERPOLATOR
    override val repeatMode = RippleEffect.DEFAULT_REPEAT_MODE

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