package me.khruslan.tierlistmaker.presentation.utils.hints.core


/**
 * Listener of the hint events that must be handled on a hint group level.
 */
interface OnHintListener {

    /**
     * Invoked on navigation to the previous hint.
     */
    fun onPrevious()

    /**
     * Invoked on navigation to the next hint.
     */
    fun onNext()

    /**
     * Invoked when the hint group must be closed.
     */
    fun onCloseGroup()
}