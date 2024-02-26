package me.khruslan.tierlistmaker.presentation.utils.hints.collection

import me.khruslan.tierlistmaker.presentation.utils.hints.core.Indexable

/**
 * Hint steps of the [CollectionHintGroup].
 *
 * @constructor Default empty constructor.
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

    /**
     * Hint step position determined by [Enum.ordinal].
     */
    override val index = ordinal
}