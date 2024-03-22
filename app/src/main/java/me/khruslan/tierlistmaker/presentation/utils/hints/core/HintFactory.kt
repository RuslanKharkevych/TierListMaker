package me.khruslan.tierlistmaker.presentation.utils.hints.core

/**
 * Abstract factory that allows to create hints for various hint step types.
 *
 * @param T Type of the hint step.
 * @constructor Default no-arg constructor.
 */
abstract class HintFactory<T: HintStep> {

    /**
     * Creates a hint for the provided step.
     *
     * @param step Step of the hint.
     * @return Created hint.
     */
    abstract fun create(step: T): Hint
}