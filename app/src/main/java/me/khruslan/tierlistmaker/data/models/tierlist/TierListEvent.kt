package me.khruslan.tierlistmaker.data.models.tierlist

import android.net.Uri
import androidx.annotation.StringRes
import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect

/**
 * Base class that represents the event that happened in a tier list.
 * Often it is a result of a [DragEffect].
 */
sealed class TierListEvent

/**
 * [TierListEvent] implementation that indicates that backlog images were changed.
 */
data object BacklogDataChanged : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that new backlog images were added.
 */
data object BacklogImagesAdded : TierListEvent()

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
data object TierListChanged : TierListEvent()

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
data object ImageRemoved : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that tier list image file is ready to be shared.
 *
 * @property uri URI reference that points to the file.
 */
data class TierListReadyToShare(val uri: Uri) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that tier list image file is ready to be viewed.
 *
 * @property uri URI reference that points to the file.
 */
data class TierListReadyToView(val uri: Uri) : TierListEvent()

/**
 * [TierListEvent] implementation that indicates that an error occurred during tier list image file
 * export.
 *
 * @property errorMessageResId string resource of the user-friendly error message.
 */
data class TierListExportError(@StringRes val errorMessageResId: Int) : TierListEvent()