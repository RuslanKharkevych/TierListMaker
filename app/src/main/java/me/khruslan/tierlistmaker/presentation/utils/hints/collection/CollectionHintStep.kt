package me.khruslan.tierlistmaker.presentation.utils.hints.collection

import me.khruslan.tierlistmaker.presentation.utils.hints.core.Indexable

/**
 * Hint steps of the [CollectionHintGroup].
 */
enum class CollectionHintStep : Indexable {

    /**
     * How to reorder tier lists.
     */
    ReorderTierLists,

    /**
     * How to add a new tier list.
     */
    AddNewTierList,

    /**
     * How to remove a tier list.
     */
    RemoveTierList;

    override val index = ordinal
}