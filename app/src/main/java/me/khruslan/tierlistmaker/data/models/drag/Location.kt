package me.khruslan.tierlistmaker.data.models.drag

import android.graphics.PointF

/**
 * Model that contains general information about the drag target. Suitable for all target types. In
 * some cases can be mapped to [TargetLocation].
 *
 * @property target drag target info.
 * @property positionInTarget position of the cursor relative to the target.
 * @see [TargetLocation]
 */
data class DragLocation(
    val target: DragData,
    val positionInTarget: PointF
)

/**
 * Model that contains specific information about the drag target. Suitable only for targets of
 * [ImageDragData] and [TierDragData] types. Is useful for determining position of the drag cursor
 * on the y-axis withing the tier list.
 *
 * @property targetPosition adapter position of the tier where the target is located.
 * @property targetOffset vertical offset of the cursor relative to the target.
 * @see [DragLocation]
 */
data class TargetLocation(
    val targetPosition: Int,
    val targetOffset: Int
)