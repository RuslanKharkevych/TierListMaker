package me.khruslan.tierlistmaker.presentation.views

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import me.khruslan.tierlistmaker.databinding.ViewHintOverlayBinding
import me.khruslan.tierlistmaker.presentation.utils.hints.core.Hint
import me.khruslan.tierlistmaker.presentation.utils.setOnThrottledClickListener

/**
 * View of the [Hint] overlay.
 *
 * Includes hint text and buttons: "Previous", "Next" and "Close". This view doesn't support XML
 * attributes. It must be instantiated only in Java/Kotlin code.
 *
 * @param context Activity context.
 * @constructor Creates the hint overlay view with empty hint text and enabled previous/next
 * buttons.
 */
class HintOverlayView(context: Context) : ConstraintLayout(context) {

    /**
     * Binding of the hint overlay view.
     */
    private val binding = ViewHintOverlayBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Updates the hint text.
     *
     * @param resId The resource identifier of the string to be displayed.
     */
    fun setHintText(@StringRes resId: Int) {
        binding.textHint.setText(resId)
    }

    /**
     * Disables "Previous" button.
     *
     * The disabled button is not clickable.
     */
    fun disablePreviousButton() {
        binding.btnPrevious.isEnabled = false
    }

    /**
     * Disables "Next" button.
     *
     * The disabled button is not clickable.
     */
    fun disableNextButton() {
        binding.btnNext.isEnabled = false
    }

    /**
     * Registers click listener for the "Previous" button.
     *
     * @param onClick Invoked when "Previous" button is clicked.
     */
    fun setOnPreviousButtonClickListener(onClick: () -> Unit) {
        binding.btnPrevious.setOnThrottledClickListener(onClick)
    }

    /**
     * Registers click listener for the "Next" button.
     *
     * @param onClick Invoked when "Next" button is clicked.
     */
    fun setOnNextButtonClickListener(onClick: () -> Unit) {
        binding.btnNext.setOnThrottledClickListener(onClick)
    }

    /**
     * Registers click listener for the "Close" button.
     *
     * @param onClick Invoked when "Close" button is clicked.
     */
    fun setOnCloseButtonClickListener(onClick: () -> Unit) {
        binding.btnClose.setOnThrottledClickListener(onClick)
    }
}