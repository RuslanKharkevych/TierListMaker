package me.khruslan.tierlistmaker.utils.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData

/**
 * A "pocket" for the [DragData] that needs to be temporarily kept in memory.
 */
interface DragPocket {

    /**
     * The shadow is the [ImageDragData] that is currently dragged.
     * Shadow should be put in the pocket when the drag is started.
     * Once the drag is ended, shadow can be used to restore image data.
     */
    var shadow: ImageDragData?

    /**
     * The target is the [DragData] of the dragged data's location.
     * Target should be updated each time the drag location data changes.
     */
    var target: DragData?

    /**
     * The "cached" target is the last non-nullable [target].
     * Cached target is used as a backup when [target] becomes null before dropping.
     */
    val cachedTarget: DragData?
}