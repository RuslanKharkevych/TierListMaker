package me.khruslan.tierlistmaker.presentation.utils.hints.core

/**
 * Classes that implement this interface can be ordered together in a group.
 */
interface Indexable {

    /**
     * An integer index that defines the position of an object within a group.
     */
    val index: Int
}