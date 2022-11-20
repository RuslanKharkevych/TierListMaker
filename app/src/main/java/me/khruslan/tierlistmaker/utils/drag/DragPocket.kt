package me.khruslan.tierlistmaker.utils.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import timber.log.Timber

/**
 * A "pocket" for the [DragData] that needs to be temporarily kept in memory.
 */
class DragPocket {

    /**
     * The shadow is the [ImageDragData] that is currently dragged.
     * Shadow should be put in the pocket when the drag is started.
     * Once the drag is ended, shadow can be used to restore image data.
     */
    var shadow: ImageDragData? = null
        get() {
            if (field == null) logError("get shadow: shadow is null")
            return field.also { field = null }
        }
        set(value) {
            if (field != null) logError("set shadow: $field is not null")
            field = value
        }

    /**
     * The target is the [DragData] of the dragged data's location.
     * Target should be updated each time the drag location data changes.
     */
    var target: DragData? = null
        get() = field.also { field = null }
        set(value) {
            field = value
            if (value != null) cachedTarget = value
        }

    /**
     * The "cached" target is the last non-nullable [target].
     * Cached target is used as a backup when [target] becomes null before dropping.
     */
    var cachedTarget: DragData? = null
        get() {
            if (field == null) logError("get cachedTarget: cachedTarget is null")
            return field.also { field = null }
        }
        private set

    /**
     * Logs unexpected pocket states.
     *
     * @param message description of an error.
     */
    private fun logError(message: String) {
        val exception = DragPocketException(message)
        Timber.e(exception, "Unexpected pocket state")
    }

    /**
     * [Exception] implementation for the errors that could happen inside the [DragPocket].
     *
     * @param message error message of the exception.
     */
    private class DragPocketException(message: String): Exception(message)
}