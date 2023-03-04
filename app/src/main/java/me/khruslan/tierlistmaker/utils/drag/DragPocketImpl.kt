package me.khruslan.tierlistmaker.utils.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [DragPocket].
 */
class DragPocketImpl @Inject constructor() : DragPocket {

    override var shadow: ImageDragData? = null
        get() {
            if (field == null) logError("get shadow: shadow is null")
            return field.also { field = null }
        }

    override var target: DragData? = null
        get() = field.also { field = null }
        set(value) {
            field = value
            if (value != null) cachedTarget = value
        }

    override var cachedTarget: DragData? = null
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
    private class DragPocketException(message: String) : Exception(message)
}