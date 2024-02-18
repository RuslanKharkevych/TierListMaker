package me.khruslan.tierlistmaker.data.models.tierlist

import android.net.Uri
import androidx.annotation.StringRes
import me.khruslan.tierlistmaker.data.models.drag.effects.DragEffect
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image

/**
 * Base class that represents the event that happened in a [TierList].
 *
 * Often, but not exclusively, it is a result of a [DragEffect]. All tier list events are immutable.
 *
 * @constructor Default constructor for use by subclasses.
 */
sealed class TierListEvent

/**
 * Tier list event that indicates that backlog images were changed.
 *
 * This event is sent when images were removed or updated inside the backlog as a result of a drag
 * effect.
 */
data object BacklogDataChanged : TierListEvent()

/**
 * Tier list event that indicates that new backlog images were added.
 *
 * This event is sent when new images were added or when images were moved to the backlog as a
 * result of a tier removal by user.
 */
data object BacklogImagesAdded : TierListEvent()

/**
 * Tier list event that indicates that backlog image was inserted at given position.
 *
 * This event is sent when an image was dropped, restored or highlighted inside the backlog as a
 * result of a drag effect.
 *
 * @property itemPosition Position of the inserted image in the backlog.
 * @constructor Creates the event from item position in backlog.
 */
data class BacklogItemInserted(val itemPosition: Int) : TierListEvent()

/**
 * Tier list event that indicates that the entire [TierList] was updated.
 *
 * This event is sent when tier styles were updated. It happens when tier list is opened or
 * invalidated when a tier is added or removed by user.
 */
data object TierListChanged : TierListEvent()

/**
 * Tier list event that indicates that specific [Tier] was updated.
 *
 * This event is sent when images were inserted, updated or removed inside the tier as a result of a
 * drag effect.
 *
 * @property tierPosition Position of the updated tier.
 * @constructor Creates the event fom tier position.
 */
data class TierChanged(val tierPosition: Int) : TierListEvent()

/**
 * Tier list event that indicates that the new [Tier] was inserted at given position.
 *
 * This event is sent when the new tier was added by user.
 *
 * @property tierPosition Position of the inserted tier.
 * @constructor Creates the event from tier position.
 */
data class TierInserted(val tierPosition: Int) : TierListEvent()

/**
 * Tier list event that indicates that [Image] size was updated.
 *
 * This event is sent when [TierList.zoomValue] changed as a result of user's action.
 *
 * @property imageSize Size of the tier list image.
 * @constructor Creates the event from image size.
 */
data class ImageSizeUpdated(val imageSize: Int) : TierListEvent()

/**
 * Tier list that indicates that the highlight of the trash bin was updated.
 *
 * This event is sent when the trash bin was highlighted or unhighlighted as a result of a drag
 * effect.
 *
 * @property highlighted Whether the trash bin becomes highlighted.
 * @constructor Creates the event from the highlighted flag.
 */
data class TrashBinHighlightUpdated(val highlighted: Boolean) : TierListEvent()

/**
 * Tier list event that indicates that [Image] was removed.
 *
 * This event is sent when an image was dropped into the trash bin as a result of a drag effect.
 */
data object ImageRemoved : TierListEvent()

/**
 * Tier list event that indicates that tier list image file is ready to be shared.
 *
 * This event is sent when the tier list was successfully saved to the file as a result of a share
 * action triggered by user.
 *
 * @property uri URI reference that points to the file.
 * @constructor Creates the event from URI.
 */
data class TierListReadyToShare(val uri: Uri) : TierListEvent()

/**
 * Tier list event that indicates that tier list image file is ready to be viewed.
 *
 * This event is sent when the tier list was successfully saved to the file as a result of a view
 * action triggered by user.
 *
 * @property uri URI reference that points to the file.
 * @constructor Creates the event from URI.
 */
data class TierListReadyToView(val uri: Uri) : TierListEvent()

/**
 * Tier list event that indicates that an error occurred during tier list image file export.
 *
 * This event is sent when the tier list wasn't saved to the file due to an error. It can happen
 * when user tries to share or view the tier list.
 *
 * @property errorMessageResId String resource of the user-friendly error message.
 * @constructor Creates the event from error message string resource.
 */
data class TierListExportError(@StringRes val errorMessageResId: Int) : TierListEvent()