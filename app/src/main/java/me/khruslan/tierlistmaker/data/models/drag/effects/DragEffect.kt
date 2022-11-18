package me.khruslan.tierlistmaker.data.models.drag.effects

/**
 * Base class that represents the effect of a drag event in a tier list.
 * Each drag effect is the immutable state that contains data needed to apply the effect.
 * This class does not have direct concrete implementations.
 *
 * @see HighlightEffect
 * @see InsertEffect
 * @see RemoveEffect
 * @see UpdateEffect
 */
sealed class DragEffect
