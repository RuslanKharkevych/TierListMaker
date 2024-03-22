package me.khruslan.tierlistmaker.presentation.utils.hints.tierlist

import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintStep

/**
 * Hint steps of the [TierListHintGroup].
 *
 * @constructor Default empty constructor.
 */
enum class TierListHintStep : HintStep {

    /**
     * How to reorder tiers.
     */
    ReorderTiers,

    /**
     * How to remove image.
     */
    RemoveImage,

    /**
     * How to remove tier.
     */
    RemoveTier;

    /**
     * Hint step position determined by [Enum.ordinal].
     */
    override val index = ordinal
}