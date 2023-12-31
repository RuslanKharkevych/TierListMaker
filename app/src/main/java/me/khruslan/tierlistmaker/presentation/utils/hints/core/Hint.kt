package me.khruslan.tierlistmaker.presentation.utils.hints.core

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.effet.Effect
import com.takusemba.spotlight.effet.EmptyEffect
import com.takusemba.spotlight.shape.Shape
import me.khruslan.tierlistmaker.presentation.utils.hints.core.shapes.EmptyShape
import me.khruslan.tierlistmaker.presentation.views.HintOverlayView
import timber.log.Timber

/**
 * Hint is a wrapper on [Target]. It includes the highlighted spot as well as the overlay.
 *
 * @param name name of the hint.
 * @param overlay overlay view of the target.
 * @param anchor anchor view of the target or null if anchor doesn't exist.
 * @param shape shape of the target or null if anchor doesn't exist.
 * @param effect effect of the target or null if anchor doesn't exist.
 */
class Hint(
    name: String,
    overlay: HintOverlayView,
    anchor: View?,
    shape: Shape?,
    effect: Effect?
) {

    /**
     * Listener of hint events.
     */
    private var onHintListener: OnHintListener? = null

    /**
     * Target of the current hint built from the [Hint] params. If anchor is missing - the entire
     * overlay is used as an anchor. If shape is missing - [EmptyShape] is used. If effect is
     * missing - [EmptyEffect] is used.
     */
    val target = Target.Builder()
        .setOverlay(overlay)
        .setAnchor(anchor ?: overlay)
        .setShape(shape ?: EmptyShape())
        .setEffect(effect ?: EmptyEffect())
        .setOnTargetListener(TargetListener(name))
        .build()

    init {
        initTouchListener(overlay, anchor)
        initButtonListeners(overlay)
    }

    /**
     * Sets [onHintListener].
     *
     * @param listener the listener to be set.
     */
    fun setOnHintListener(listener: OnHintListener) {
        onHintListener = listener
    }

    /**
     * Initializes the touch listener of the overlay view. If user touches the area inside the
     * anchor view, closes the hint group and propagates the touch event. If user touches the area
     * outside of the anchor view, consumes the touch event.
     *
     * @param overlay overlay view.
     * @param anchor anchor view or null if anchor doesn't exist.
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun initTouchListener(overlay: HintOverlayView, anchor: View?) {
        overlay.setOnTouchListener { _, event ->
            val anchorRect = Rect()
            anchor?.getGlobalVisibleRect(anchorRect)
            val isAnchorTouched = anchorRect.contains(event.x.toInt(), event.y.toInt())
            if (isAnchorTouched) onHintListener?.onCloseGroup()
            !isAnchorTouched
        }
    }

    /**
     * Initializes listeners of the buttons in the overlay view.
     */
    private fun initButtonListeners(overlay: HintOverlayView) {
        with(overlay) {
            setOnPreviousButtonClickListener { onHintListener?.onPrevious() }
            setOnNextButtonClickListener { onHintListener?.onNext() }
            setOnCloseButtonClickListener { callOnCloseGroup() }
        }
    }

    /**
     * Invokes [OnHintListener.onCloseGroup] event. Additionally calls [TargetListener.onEnded] to
     * trigger hint ended log output.
     */
    private fun callOnCloseGroup() {
        target.listener?.onEnded()
        onHintListener?.onCloseGroup()
    }

    /**
     * Listener that logs target started / ended events.
     *
     * @property name name of the hint.
     */
    private class TargetListener(private val name: String) : OnTargetListener {

        override fun onStarted() {
            Timber.i("$name hint started")
        }

        override fun onEnded() {
            Timber.i("$name hint ended")
        }
    }
}