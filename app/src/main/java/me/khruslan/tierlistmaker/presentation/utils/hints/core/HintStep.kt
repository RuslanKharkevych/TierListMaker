package me.khruslan.tierlistmaker.presentation.utils.hints.core

/**
 * Classes that implement this interface can be ordered together in a hint group.
 */
interface HintStep {

    /**
     * An integer index that defines the position of a hint within a hint group.
     */
    val index: Int
}