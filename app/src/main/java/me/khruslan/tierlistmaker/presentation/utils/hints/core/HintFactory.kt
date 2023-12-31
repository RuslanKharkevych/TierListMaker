package me.khruslan.tierlistmaker.presentation.utils.hints.core

/**
 * Abstract factory that allows to create hints for various hint step types.
 *
 * @param T type of the hint step.
 */
abstract class HintFactory<T: Indexable> {

    /**
     * Creates a hint for the provided step.
     *
     * @param step step of the hint.
     * @return created hint.
     */
    abstract fun create(step: T): Hint
}