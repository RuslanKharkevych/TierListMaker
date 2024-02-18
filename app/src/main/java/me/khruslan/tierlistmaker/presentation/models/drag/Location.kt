package me.khruslan.tierlistmaker.presentation.models.drag

import android.graphics.PointF
import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData

/**
 * Model that contains general information about the drag target.
 *
 * Suitable for all target types. In some cases can be mapped to [TargetLocation].
 *
 * @property target Drag target info.
 * @property positionInTarget Position of the cursor relative to the target.
 * @constructor Creates a new drag location instance.
 * @see TargetLocation
 */
data class DragLocation(
    val target: DragData,
    val positionInTarget: PointF
)

/**
 * Model that contains specific information about the drag target.
 *
 * Suitable only for targets of [ImageDragData] and [TierDragData] types. Is useful for determining
 * position of the drag cursor on the y-axis withing the tier list.
 *
 * @property adapterPosition Adapter position of the tier where the target is located.
 * @property verticalOffset Vertical offset of the cursor relative to the target.
 * @constructor Creates a new target location instance.
 * @see DragLocation
 */
data class TargetLocation(
    val adapterPosition: Int,
    val verticalOffset: Int
)