package me.khruslan.tierlistmaker.data.providers.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import timber.log.Timber
import javax.inject.Inject

/**
 * [DragPocket] implementations.
 *
 * Catches and logs unexpected pocket states.
 *
 * @constructor Creates a new drag pocket instance.
 */
class DragPocketImpl @Inject constructor() : DragPocket {

    /**
     * The shadow is the data that is currently dragged.
     *
     * If shadow is read before it's initialized, returns null and logs error.
     */
    override var shadow: ImageDragData? = null
        get() {
            if (field == null) logError("get shadow: shadow is null")
            return field.also { field = null }
        }

    /**
     * The target is the drag location data.
     *
     * Once target is read, the field is cleared. When target is assigned with a non-nullable value,
     * [cachedTarget] is also set with the same value.
     */
    override var target: DragData? = null
        get() = field.also { field = null }
        set(value) {
            field = value
            if (value != null) cachedTarget = value
        }

    /**
     * The "cached" target is the last non-nullable target.
     *
     * Once cached target is read, the field is cleared. If it's read before it's initialized,
     * returns null and logs error.
     */
    override var cachedTarget: DragData? = null
        get() {
            if (field == null) logError("get cachedTarget: cachedTarget is null")
            return field.also { field = null }
        }
        private set

    /**
     * Logs unexpected pocket states.
     *
     * @param message Description of an error.
     */
    private fun logError(message: String) {
        val exception = DragPocketException(message)
        Timber.e(exception, "Unexpected pocket state")
    }

    /**
     * Indicates unexpected pocket state.
     *
     * @param message Error message.
     * @constructor Creates the exception with message.
     */
    private class DragPocketException(message: String) : Exception(message)
}