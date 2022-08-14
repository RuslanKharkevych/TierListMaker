package me.khruslan.tierlistmaker.data.tierlist

import me.khruslan.tierlistmaker.data.drag.effects.DragEffect

/**
 * Base class that represents the event that happened in a tier list.
 * Often it is a result of a [DragEffect].
 */
sealed class TierListEvent

/**
 * [TierListEvent] implementation that indicates that backlog images were changed.
 */
object BacklogDataChanged : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that new backlog images were added.
 */
object BacklogImagesAdded : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that specific backlog image was updated.
 *
 * @property itemPosition position of the updated image in the backlog.
 */
data class BacklogItemChanged(val itemPosition: Int) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that backlog image was inserted at given position.
 *
 * @property itemPosition position of the inserted image in the backlog.
 */
data class BacklogItemInserted(val itemPosition: Int) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that the entire tier list was updated.
 */
object TierListChanged : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that specific tier was updated.
 *
 * @property tierPosition position of the updated tier.
 */
data class TierChanged(val tierPosition: Int) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that the new tier was inserted at given position.
 *
 * @property tierPosition position of the inserted tier.
 */
data class TierInserted(val tierPosition: Int) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that image size was updated.
 *
 * @property imageSize size of the tier list image.
 */
data class ImageSizeUpdated(val imageSize: Int) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that the highlight of the trash bin was updated.
 *
 * @property highlighted whether the trash bin becomes highlighted.
 */
data class TrashBinHighlightUpdated(val highlighted: Boolean) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that image was removed.
 */
object ImageRemoved : TierListEvent()