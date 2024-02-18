package me.khruslan.tierlistmaker.data.providers.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData

/**
 * A "pocket" for the drag data that needs to be temporarily kept in memory.
 *
 * The concrete implementation of this interface might apply additional preconditions and
 * postconditions.
 */
interface DragPocket {

    /**
     * The shadow is the data that is currently dragged.
     *
     * Shadow should be put in the pocket when the drag is started. Once the drag is ended, shadow
     * can be used to restore image data.
     */
    var shadow: ImageDragData?

    /**
     * The target is the drag location data.
     *
     * Target should be updated each time the drag location changes.
     */
    var target: DragData?

    /**
     * The "cached" target is the last non-nullable [target].
     *
     * Cached target is used as a backup when the target location is cleared before dropping.
     */
    val cachedTarget: DragData?
}