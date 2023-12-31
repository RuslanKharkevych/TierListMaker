package me.khruslan.tierlistmaker.presentation.views

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import me.khruslan.tierlistmaker.databinding.ViewHintOverlayBinding
import me.khruslan.tierlistmaker.presentation.utils.hints.core.Hint

/**
 * View of the [Hint] overlay. Includes hint text and buttons: "Previous", "Next" and "Close". This
 * view doesn't support XML attributes. It must be instantiated only in Java/Kotlin code.
 *
 * @param context activity context.
 */
class HintOverlayView(context: Context) : ConstraintLayout(context) {
    private val binding = ViewHintOverlayBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Sets hint text.
     *
     * @param resId the resource identifier of the string to be displayed.
     */
    fun setHintText(@StringRes resId: Int) {
        binding.textHint.setText(resId)
    }

    /**
     * Disables "Previous" button.
     */
    fun disablePreviousButton() {
        binding.btnPrevious.isEnabled = false
    }

    /**
     * Disables "Next" button.
     */
    fun disableNextButton() {
        binding.btnNext.isEnabled = false
    }

    /**
     * Sets click listener to the "Previous" button.
     *
     * @param listener the listener to set.
     */
    fun setOnPreviousButtonClickListener(listener: OnClickListener) {
        binding.btnPrevious.setOnClickListener(listener)
    }

    /**
     * Sets click listener to the "Next" button.
     *
     * @param listener the listener to set.
     */
    fun setOnNextButtonClickListener(listener: OnClickListener) {
        binding.btnNext.setOnClickListener(listener)
    }

    /**
     * Sets click listener to the "Close" button.
     *
     * @param listener the listener to set.
     */
    fun setOnCloseButtonClickListener(listener: OnClickListener) {
        binding.btnClose.setOnClickListener(listener)
    }
}